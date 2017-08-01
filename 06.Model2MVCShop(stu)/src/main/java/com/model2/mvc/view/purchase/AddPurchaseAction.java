package com.model2.mvc.view.purchase;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.impl.ProductServiceImpl;

public class AddPurchaseAction extends Action {

	public AddPurchaseAction() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Purchase purchase = new Purchase();
		User buyer = new User();
		Product product = new Product();

		buyer = (User)request.getSession().getAttribute("user");
		

		product = productServiceImpl.getProduct
					(Integer.parseInt(request.getParameter("prodNo")));

		
		purchase.setBuyer(buyer);
		purchase.setPurchaseProd(product);
		
		purchase.setReceiverName(request.getParameter("receiverName"));
		purchase.setDlvyAddr(request.getParameter("receiverAddr"));
		purchase.setReceiverPhone(request.getParameter("receiverPhone"));
		
		purchase.setPaymentOption(request.getParameter("paymentOption"));
		purchase.setDlvyRequest(request.getParameter("receiverRequest"));
		purchase.setDlvyDate(request.getParameter("receiverDate").replaceAll("-", ""));

		
//		PurchaseService service = new PurchaseServiceImp();
//		service.addPurchase(purchase);
		purchaseServiceImpl.addPurchase(purchase);
		
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("prodNo", request.getParameter("prodNo"));
		map.put("tranCode", "1");
		productServiceImpl.updateProductTranCode(map);
		
		
		
		request.setAttribute("purchase", purchase);
		
		return "forward:/purchase/addPurchase.jsp";
	}

}
