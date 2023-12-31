package com.zuimeihui.demo.service.controller;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jasig.cas.client.boot.configuration.CasClientConfigurationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
 
@Controller("webIndexController")
public class IndexController {
//    @Autowired
//    private SecretConfig secretConfig;
    @Autowired
    private CasClientConfigurationProperties casClientConfigurationProperties;
    @RequestMapping("/")
    public void toIndexDefault(HttpServletRequest request, HttpServletResponse resp) throws IOException {
        String userId = (String) request.getSession().getAttribute("userId");
//        String redirectUri = secretConfig.getLoginSuccessUrl()+userId;
//        resp.sendRedirect(redirectUri);
    }
 
    @GetMapping("/index")
    public void toIndex(HttpServletRequest request,HttpServletResponse resp) throws IOException {
        // 获取当前的会话对象
        HttpSession session = request.getSession();
        // 获取所有会话属性名称
        Enumeration<String> attributeNames = session.getAttributeNames();
        // 遍历会话属性名称并打印属性值
        while (attributeNames.hasMoreElements()) {
            String attributeName = attributeNames.nextElement();
            Object attributeValue = session.getAttribute(attributeName);
            System.out.println(attributeName + ": " + attributeValue);
        }
        String userId = (String) request.getSession().getAttribute("userId");
//        String redirectUri = secretConfig.getLoginSuccessUrl()+userId;
//        resp.sendRedirect(redirectUri);
    }
    @GetMapping("/portal/logout")
    public String toLogout(HttpServletRequest request) {
        request.getSession().invalidate();
//        String logouturl = secretConfig.getLogoutSuccessUrl();
//        return "redirect:" + casClientConfigurationProperties.getServerUrlPrefix() + "/logout?service=" + logouturl ;
        return "redirect:" + casClientConfigurationProperties.getServerUrlPrefix() + "/logout?service=" + "aaaaaaaaaaaaaaaaaaaaaaaaaaa" ;
    }
}
