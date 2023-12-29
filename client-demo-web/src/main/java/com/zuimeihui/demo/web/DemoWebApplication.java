package com.zuimeihui.demo.web;

import org.jasig.cas.client.boot.configuration.EnableCasClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 程序入口
 * 
 * @author 醉美会 ZuiMeiHui.com
 * @date 2023-11-18 10:37:30
 */
@EnableCasClient
@SpringBootApplication
public class DemoWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoWebApplication.class, args);
	}
	
}
