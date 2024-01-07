package com.zuimeihui.demo.common.service;

import java.io.IOException;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

/**
 * 封装拦截器
 * 
 * @ClassName: RequestLoggingInterceptor
 * @Description: TODO
 * @author ZuiMeiHui.com 醉美会
 */
public class RequestLoggingInterceptor implements ClientHttpRequestInterceptor {

	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
		ClientHttpResponse response = execution.execute(request, body);
		return response;
	}

}
