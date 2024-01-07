package com.zuimeihui.demo.common.service;

import java.util.Collections;

import javax.net.ssl.SSLContext;

import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.ssl.TrustStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * 封装RestTemplate
 * 
 * @ClassName: RestTemplateCustom
 * @Description: TODO
 * @author ZuiMeiHui.com 醉美会
 */
@Component
public class RestTemplateCustom extends RestTemplate {

	@Autowired
	public RestTemplateCustom() {
		this.setRequestFactory(generateHttpsRequestFactory());
		setInterceptors(Collections.singletonList(new RequestLoggingInterceptor()));
		getMessageConverters().add(new MappingJackson2HttpMessageConverter());
	}

	private HttpComponentsClientHttpRequestFactory generateHttpsRequestFactory() {
		try {
			TrustStrategy acceptingTrustStrategy = ((x509Certificates, authType) -> true);
			SSLContext sslContext = SSLContexts.custom().loadTrustMaterial(null, acceptingTrustStrategy).build();
			SSLConnectionSocketFactory connectionSocketFactory = new SSLConnectionSocketFactory(sslContext, new NoopHostnameVerifier());
			HttpClientBuilder httpClientBuilder = HttpClients.custom();
			httpClientBuilder.setSSLSocketFactory(connectionSocketFactory);
			CloseableHttpClient httpClient = httpClientBuilder.build();
			HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
			factory.setHttpClient(httpClient);
			factory.setConnectTimeout(10 * 1000);
			factory.setReadTimeout(30 * 1000);
			return factory;
		} catch (Exception e) {
			throw new RuntimeException("创建httpsRestTemplate失败", e);
		}
	}

}
