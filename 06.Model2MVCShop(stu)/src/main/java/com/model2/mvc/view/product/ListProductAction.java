package com.model2.mvc.view.product;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;


public class ListProductAction extends Action {
	
	public ListProductAction() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Search search = new Search();

		int currentPage = 1;
		
		if(request.getParameter("currentPage") != null && !(request.getParameter("currentPage")).equals("")){
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
			System.out.println("이번에 눌린 커런트 페이지"+currentPage);
		}
		
		String searchSortPrice = "0";
		if(request.getParameter("searchSortPrice") != null && !(request.getParameter("searchSortPrice")).equals("")){
			searchSortPrice = request.getParameter("searchSortPrice");
			System.out.println("가격정렬 기준"+searchSortPrice);
		}
		
		search.setCurrentPage(currentPage);
		search.setSearchCondition(request.getParameter("searchCondition"));
		System.out.println("리프액 searchCondition"+request.getParameter("searchCondition"));
		search.setSearchKeyword(request.getParameter("searchKeyword"));
		System.out.println("리프액 searchKeyword"+request.getParameter("searchKeyword"));
		search.setSearchSortPrice(request.getParameter("searchSortPrice"));
		System.out.println("리프액 sortPrice"+request.getParameter("searchSortPrice"));
		int pageSize = Integer.parseInt(getServletContext().getInitParameter("pageSize"));
		int pageUnit = Integer.parseInt(getServletContext().getInitParameter("pageUnit"));
		search.setPageUnit(pageUnit);
		
		
		//ProductService service=new ProductServiceImpl();
		//Map<String, Object> map = service.getProductList(search);
		Map<String, Object> map = productServiceImpl.getProductList(search);
		
		
		Page resultPage	= 
				new Page( currentPage, ((Integer)map.get("totalCount")).intValue(),
						pageUnit, pageSize);
		System.out.println("ListProductAction ::"+resultPage);
	
		
		System.out.println(map.get("list").toString()+"확인용");
		request.setAttribute("list",map.get("list"));
		request.setAttribute("search",search);
		request.setAttribute("resultPage", resultPage);
		
		
		return "forward:/product/listProduct.jsp";
	}

}
