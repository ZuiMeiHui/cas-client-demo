package com.zuimeihui.demo.web.controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.jasig.cas.client.authentication.AttributePrincipal;
import org.jasig.cas.client.validation.Assertion;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

	@ResponseBody
	@RequestMapping("/login")
	public String login(HttpSession session) {
		System.out.println(session);
		Assertion assertion = (Assertion) session.getAttribute("_const_cas_assertion_");
		AttributePrincipal principal = assertion.getPrincipal();
		String loginName = principal.getName();

		return "sso-test1，当前登录账户" + loginName;
	}

	/**
	 * 退出 后自动重定向自定义接口
	 */
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
		return "redirect:http://127.0.0.1:8443/cas/logout?service=http://127.0.0.1/logout-success";

	}

	/**
	 * 退出 后自动重定向自定义接口
	 */
	@RequestMapping("/logout-success")
	public String logoutSuccess(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
		return "logout success !!!";

	}

}
