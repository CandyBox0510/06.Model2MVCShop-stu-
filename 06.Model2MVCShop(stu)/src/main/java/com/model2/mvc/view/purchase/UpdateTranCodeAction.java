package com.model2.mvc.view.purchase;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.purchase.PurchaseService;

public class UpdateTranCodeAction extends Action {

	public UpdateTranCodeAction() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

			Map<String, Object>map = new HashMap<String, Object>();
			map.put("tranNo", Integer.parseInt(request.getParameter("tranNo")));
			map.put("tranCode", Integer.parseInt(request.getParameter("tranCode")));
			purchaseServiceImpl.updateTranCode(map);
			map.put("prodNo", productServiceImpl.getProductNo(Integer.parseInt(request.getParameter("tranNo"))));
			productServiceImpl.updateProductTranCode(map);
			
			
		return "forward:/listPurchase.do";
	}

}
