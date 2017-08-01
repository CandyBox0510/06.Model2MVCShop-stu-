package com.model2.mvc.view.purchase;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.purchase.PurchaseService;


public class CancelPurchaseAction extends Action {

	public CancelPurchaseAction() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
//		PurchaseService service = new PurchaseServiceImp();
//		Purchase purchase = service.getPurchase(Integer.parseInt(request.getParameter("tranNo")));
//		Purchase purchase = purchaseServiceImpl.getPurchase(Integer.parseInt(request.getParameter("tranNo")));
		Map<String, Object>map = new HashMap<String, Object>();
		map.put("prodNo", productServiceImpl.getProductNo(Integer.parseInt(request.getParameter("tranNo"))));
		map.put("tranCode", null);
		productServiceImpl.updateProductTranCode(map);
		purchaseServiceImpl.deletePurchase(request.getParameter("tranNo"));
		
		
		return "forward:/listPurchase.do";
	}

}