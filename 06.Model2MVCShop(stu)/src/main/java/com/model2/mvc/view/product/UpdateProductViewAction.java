package com.model2.mvc.view.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;

public class UpdateProductViewAction extends Action {

	public UpdateProductViewAction() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String prodNo=request.getParameter("prodNo");
		
//		ProductService service=new ProductServiceImpl();
//		Product product = service.getProduct(Integer.parseInt(prodNo));
		Product product = productServiceImpl.getProduct(Integer.parseInt(prodNo));
		request.setAttribute("product", product);
		
		
		return "forward:/product/updateProductView.jsp";
	}

}
