package com.model2.mvc.view.purchase;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.domain.Wish;



public class ListWishPurchaseAction extends Action {

	public ListWishPurchaseAction() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setAttribute("wishSuccess", "");
		
		Search search = new Search();
		
		int currentPage=1;
		if(request.getParameter("currentPage") != null && !(request.getParameter("currentPage")).equals("")){
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
			System.out.println("이번에 눌린 커런트 페이지"+currentPage);
		}
		
		search.setCurrentPage(currentPage);
		int pageSize = Integer.parseInt(getServletContext().getInitParameter("pageSize"));
		int pageUnit = Integer.parseInt(getServletContext().getInitParameter("pageUnit"));
		search.setPageUnit(pageUnit);
		
		User user = (User)request.getSession().getAttribute("user");
		
//		PurchaseService service = new PurchaseServiceImp();

//		HashMap<String, Object> map = service.getWishList(search, user.getUserId());
		Map<String, Object> map = purchaseServiceImpl.getWishList(search, user.getUserId());
		
		Page resultPage	= 
				new Page( currentPage, ((Integer)map.get("totalCount")).intValue(),
						pageUnit, pageSize);
		
		List<Wish> list = (List<Wish>)map.get("list");
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).getTranStatusCode() != null){
				list.get(i).setTranStatusCode(list.get(i).getTranStatusCode().trim());
			}
			list.get(i).setPurchaseProd(productServiceImpl.getProduct(list.get(i).getPurchaseProd().getProdNo()));
		}
		
		
		request.setAttribute("resultPage", resultPage);
		request.setAttribute("list", map.get("list"));
		request.setAttribute("search",search);
		
		return "forward:/purchase/wishPurchaseView.jsp";
	}

}
