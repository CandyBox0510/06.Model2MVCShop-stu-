package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.common.util.CommonUtil;
import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.purchase.PurchaseService;

public class UpdatePurchaseAction extends Action {

	public UpdatePurchaseAction() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Purchase purchase = new Purchase();
		User user = new User();
		user.setUserId(request.getParameter("buyerId"));
		
		purchase.setBuyer(user);
		purchase.setTranNo(Integer.parseInt(request.getParameter("tranNo")));
		purchase.setPaymentOption(request.getParameter("paymentOption"));
		purchase.setReceiverName(request.getParameter("receiverName"));
		purchase.setReceiverPhone(request.getParameter("receiverPhone"));
		purchase.setDlvyAddr(request.getParameter("receiverAddr"));
		purchase.setDlvyRequest(request.getParameter("receiverRequest"));
		purchase.setDlvyDate(CommonUtil.toDateStr(request.getParameter("divyDate")));
		
//		PurchaseService service = new PurchaseServiceImp();
//		service.updatePurchase(purchase);
		purchaseServiceImpl.updatePurchase(purchase);

		return "forward:/getPurchase.do?tranNo="+request.getParameter("tranNo");
		
	}

}
