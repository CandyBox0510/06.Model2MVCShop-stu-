package com.model2.mvc.web.user;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.user.UserService;

@Controller
public class UserController {
	
	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;
	
	@Value("#{commonProperties['pageUnit'] ?: 3}")
	int pageUnit;
	
	@Value("#{commonProperties['pageSize'] ?: 2}")
	int pageSize;
	
	public UserController() {
		System.out.println("UserController Default Constructor");
	}
	
	@RequestMapping("/checkDuplication.do")
	public String checkDuplication(@RequestParam("userId") String userId, HttpServletRequest request,
										HttpServletResponse response) throws Exception{
		boolean result = userService.checkDuplication(userId);
		
		request.setAttribute("result", new Boolean(result));
		request.setAttribute("userId", userId);
		
		return "forward:/user/checkDuplication.jsp";
	}
	
	@RequestMapping("/addUser.do")
	public String addUser(@ModelAttribute("user") User user, HttpServletRequest requset,
								HttpServletResponse response) throws Exception{
		
		userService.addUser(user);
		
		return "redirect:/user/loginView.jsp";
	}
	
	@RequestMapping("/login.do")
	public String login(@ModelAttribute("user") User user,
							HttpServletRequest request,HttpSession session) throws Exception{
		User dbVO = userService.loginUser(user);
		if(dbVO == null){
			request.setAttribute("loginFail", "fail");
			return "forward:/index.jsp";
		}
		session.setAttribute("user", dbVO);

		return "redirect:/index.jsp";
	}
	
	@RequestMapping("/logout.do")
	public String logout(HttpSession session){
		session.invalidate();
		return "redirect:/index.jsp";
	}
	
	@RequestMapping("/getUser.do")
	public String getUser(@RequestParam("userId") String userId,
							HttpServletRequest request) throws Exception{

		User user=userService.getUser(userId);
		
		request.setAttribute("user", user);

		return "forward:/user/readUser.jsp";
	}
	
	@RequestMapping("/updateUserView.do")
	public String updateUserView(@RequestParam("userId") String userId,
									HttpServletRequest request) throws Exception{
		User user=userService.getUser(userId);
		
		request.setAttribute("user", user);
		
		return "forward:/user/updateUser.jsp";
		
	}
	
	@RequestMapping("/updateUser.do")
	public String updateUser(@ModelAttribute("user") User user,
								HttpSession session) throws Exception{
		String userId=user.getUserId();
		
		userService.updateUser(user);
		
		String sessionId=((User)session.getAttribute("user")).getUserId();

		if(sessionId.equals(userId)){
			session.setAttribute("user", user);
		}
		
		return "redirect:/getUser.do?userId="+userId;	
	}
	
	@RequestMapping("/listUser.do")
	public String listUser(@ModelAttribute("search")Search search, HttpServletRequest request) throws Exception{

		if(search.getCurrentPage() ==0 ){
			search.setCurrentPage(1);
		}
	
		search.setPageUnit(pageUnit);
		
		Map<String,Object> map=userService.getUserList(search);

		Page resultPage = new Page(search.getCurrentPage(), 
									(Integer)map.get("totalCount"), pageUnit, pageSize);
				
		request.setAttribute("list", map.get("list"));
		request.setAttribute("search", search);
		request.setAttribute("resultPage", resultPage);
		
		return "forward:/user/listUser.jsp";
	}
}
