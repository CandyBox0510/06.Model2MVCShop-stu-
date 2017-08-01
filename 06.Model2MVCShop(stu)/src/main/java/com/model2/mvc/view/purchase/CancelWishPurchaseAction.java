package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.purchase.PurchaseService;


public class CancelWishPurchaseAction extends Action {

	public CancelWishPurchaseAction() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
//		PurchaseService service = new PurchaseServiceImp();
//		service.deleteWishPurchase(request.getParameter("wishNo"));
		purchaseServiceImpl.deleteWishPurchase(request.getParameter("wishNo"));
		
		return "forward:/listWishPurchase.do";
	}

}
