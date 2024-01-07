package com.zuimeihui.demo.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 健康检查
 * 
 * @ClassName: MainController
 * @Description: TODO
 * @author ZuiMeiHui.com 醉美会
 */
@RestController
@RequestMapping("/")
public class MainController {

	@GetMapping({ "/main.htm" })
	public String main() {
		return "success";
	}
}
