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



public class ListPurchaseAction extends Action {

	public ListPurchaseAction() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Search search = new Search();
		String userId = null;
		
		int currentPage=1;
		if(request.getParameter("currentPage") != null && !(request.getParameter("currentPage")).equals("")){
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
			System.out.println("이번에 눌린 커런트 페이지"+currentPage);
		}
		
		search.setCurrentPage(currentPage);
		search.setSearchCondition(request.getParameter("searchCondition"));
		search.setSearchKeyword(request.getParameter("searchKeyword"));
		int pageSize = Integer.parseInt(getServletContext().getInitParameter("pageSize"));
		int pageUnit = Integer.parseInt(getServletContext().getInitParameter("pageUnit"));
		search.setPageUnit(pageUnit);

		try{
		userId = ((User)(request.getSession(true).getAttribute("user"))).getUserId();
		}catch(NullPointerException e){
			throw new Exception("로그인 풀린 상태.");
		}
//		PurchaseService service = new PurchaseServiceImp();
//		
//		HashMap<String, Object> map = service.getPurchaseList(search, buyerId);
		Map<String, Object> map = purchaseServiceImpl.getPurchaseList(search, userId);
		
		Page resultPage	= 
				new Page( currentPage, ((Integer)map.get("totalCount")).intValue(),
						pageUnit, pageSize);
		System.out.println("ListPurchaseAction ::"+resultPage);
		
		List<Purchase> list = (List<Purchase>)map.get("list");
		for (int i = 0; i < list.size(); i++) {
			list.get(i).setTranCode(list.get(i).getTranCode().trim());
			list.get(i).setPurchaseProd(productServiceImpl.getProduct(list.get(i).getPurchaseProd().getProdNo()));
		}
		
		request.setAttribute("resultPage", resultPage);
		request.setAttribute("list", map.get("list"));
		request.setAttribute("search",search);
		
		return "forward:/purchase/listPurchase.jsp";
	}

}