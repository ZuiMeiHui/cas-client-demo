package com.zuimeihui.demo.web;

import org.jasig.cas.client.boot.configuration.EnableCasClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 程序入口
 * 
 * @ClassName: LoginWebApplication
 * @Description: TODO
 * @author ZuiMeiHui.com 醉美会
 */
@EnableCasClient
@SpringBootApplication
public class LoginWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoginWebApplication.class, args);
	}

}
