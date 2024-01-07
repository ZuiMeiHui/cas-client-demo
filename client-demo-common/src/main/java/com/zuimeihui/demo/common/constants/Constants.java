package com.zuimeihui.demo.common.constants;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 常量
 * 
 * @ClassName: Constants
 * @Description: TODO
 * @author ZuiMeiHui.com 醉美会
 */
public class Constants {

	// 登陆
	public static final String CAS_SERVICE = "https://cas.zuimeihui.com:88/cas";
	public static final String CAS_OAUTH_SERVICE = "https://cas.zuimeihui.com:88/cas/oauth2.0";
	public static final String CAS_CLIENT_LOGIN = "http://login.zuimeihui.com:8080";
	public static final String CAS_CLIENT_ID = "oauth2-id-zuimeihui-com";
	public static final String CAS_CLIENT_SECRET = "oauth2-secret-zuimeihui-com";

	// 账号
	public static final String USER_TOKEN_KEY = "token";
	public static final String USER_ID_KEY = "USER_ID_KEY";
	public static final String USER_NAME_KEY = "USER_NAME_KEY";

	// 默认分页每页记录数
	public static final Integer PAGE_SIZE = 20;

	// 线程池
	public static final ThreadPoolExecutor THREAD_POOL_EXECUTOR = new ThreadPoolExecutor(1, 100, 1, TimeUnit.HOURS,
			new ArrayBlockingQueue<>(100));

}
