package com.model2.mvc.view.purchase;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.purchase.PurchaseService;


public class UpdateTranCodeByProdAction extends Action {

	public UpdateTranCodeByProdAction() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

//		PurchaseService service = new PurchaseServiceImp();
//		Purchase purchase = service.getPurchase2(Integer.parseInt(request.getParameter("prodNo")));
//		Purchase purchase = purchaseServiceImpl.getPurchase2(Integer.parseInt(request.getParameter("prodNo")));
//		purchase.setTranCode(request.getParameter("tranCode"));
//		service.updateTranCode(purchase);
		Map<String, Object>map = new HashMap<String, Object>();
		map.put("prodNo", Integer.parseInt(request.getParameter("prodNo")));
		map.put("tranCode", Integer.parseInt(request.getParameter("tranCode")));
		purchaseServiceImpl.updateTranCode(map);
		productServiceImpl.updateProductTranCode(map);
				
		return "forward:/listProduct.do?menu=manage";
	}

}