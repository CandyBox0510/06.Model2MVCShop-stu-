package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.core.ApplicationContext;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.purchase.PurchaseService;


public class AddWishPurchaseAction extends Action {

	public AddWishPurchaseAction() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Purchase purchase = new Purchase();
//		Product product = 
//				new ProductServiceImpl().getProduct(Integer.parseInt(request.getParameter("prodNo")));
		Product product = new Product();
		product.setProdNo(Integer.parseInt(request.getParameter("prodNo")));
		
		purchase.setPurchaseProd(product);
		purchase.setBuyer((User)(request.getSession().getAttribute("user"))); 
		
//		PurchaseService service = new PurchaseServiceImp();
		purchaseServiceImpl.addWishPurchase(purchase);
		
		request.setAttribute("wishSuccess", "success");

		return "forward:/listProduct.do?menu=search";
	}

}
