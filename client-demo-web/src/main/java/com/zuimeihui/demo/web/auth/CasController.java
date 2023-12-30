package com.zuimeihui.demo.web.auth;

import javax.servlet.http.HttpSession;

import org.jasig.cas.client.authentication.AttributePrincipal;
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

	/**
	 * 登陆
	 * 
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping("/login")
	public String login(HttpSession session, Model model) {
		Assertion assertion = (Assertion) session.getAttribute("_const_cas_assertion_");
		AttributePrincipal principal = assertion.getPrincipal();
		String loginName = principal.getName();
		model.addAttribute("loginName", loginName);
		model.addAttribute("message", "登陆成功 ！");
		return "login";
	}

	/**
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
