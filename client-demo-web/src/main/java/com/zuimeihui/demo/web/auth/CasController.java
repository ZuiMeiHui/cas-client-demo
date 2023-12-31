package com.zuimeihui.demo.web.auth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.jasig.cas.client.authentication.AttributePrincipal;
import org.jasig.cas.client.util.AbstractCasFilter;
import org.jasig.cas.client.util.AssertionHolder;
import org.jasig.cas.client.validation.Assertion;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * cas client 登陆/退出
 */
@Controller
@RequestMapping("/cas")
public class CasController {

//	/**
//	 * 登陆
//	 * 
//	 * @param session
//	 * @param model
//	 * @return
//	 */
//	@RequestMapping("/login")
//	public String login(HttpServletRequest request, HttpSession session, Model model) {
//		Assertion assertion = (Assertion) session.getAttribute("_const_cas_assertion_");
//		AttributePrincipal principal = assertion.getPrincipal();
//		String loginName = principal.getName();
//		Object userId = AssertionHolder.getAssertion().getPrincipal().getAttributes().get("uid");
//		Object email = AssertionHolder.getAssertion().getPrincipal().getAttributes().get("email");
//		model.addAttribute("loginName", loginName);
//		model.addAttribute("message", "登陆成功 ！");
//		return "login";
//	}
	
	/**
	 * 登陆
	 * 
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping("/login")
	public String login2(HttpServletRequest request, Model model) {
		String remoteUser = request.getRemoteUser();
		// 断言
		Assertion assertion = (Assertion) request.getSession().getAttribute(AbstractCasFilter.CONST_CAS_ASSERTION);
		AttributePrincipal principal = assertion.getPrincipal();
		String username = principal.getName();
		String user3 = AssertionHolder.getAssertion().getPrincipal().getName();
		/**
		 * 加上 其他参数以后的
		 */
		Object email = AssertionHolder.getAssertion().getPrincipal().getAttributes().get("useremail");
		Object uid = AssertionHolder.getAssertion().getPrincipal().getAttributes().get("userid");
		model.addAttribute("loginName", username);
		model.addAttribute("message", "登陆成功 ！");
		return "login";
	}


	/**2
	 * 退出
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/logout")
	public String logout(HttpSession session, Model model) {
		session.invalidate();
		return "redirect:http://127.0.0.1:8443/cas/logout?service=http://127.0.0.1:8080/cas/login";
	}

}
