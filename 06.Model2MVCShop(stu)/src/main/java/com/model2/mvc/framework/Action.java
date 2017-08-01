package com.model2.mvc.framework;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.user.UserService;


public abstract class Action {
	
	private ServletContext servletContext;
	protected UserService userServiceImpl;
	protected ProductService productServiceImpl;
	protected PurchaseService purchaseServiceImpl;
	
	public Action(){
	}
	
	public ServletContext getServletContext() {
		return servletContext;
	}

	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
		userServiceImpl = (UserService)(getServletContext().getAttribute("userServiceImpl"));
		productServiceImpl= (ProductService)(getServletContext().getAttribute("productServiceImpl"));
		purchaseServiceImpl= (PurchaseService)(getServletContext().getAttribute("purchaseServiceImpl"));
	}

	public abstract String execute(HttpServletRequest request, HttpServletResponse response) throws Exception ;
}