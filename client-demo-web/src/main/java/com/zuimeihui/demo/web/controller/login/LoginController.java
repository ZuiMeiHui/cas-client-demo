package com.zuimeihui.demo.web.controller.login;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson2.JSONObject;
import com.zuimeihui.demo.common.constants.Constants;
import com.zuimeihui.demo.common.service.ApiResult;
import com.zuimeihui.demo.common.service.RestTemplateCustom;

/**
 * 登陆
 * 
 * @ClassName: LoginController
 * @Description: TODO
 * @author ZuiMeiHui.com 醉美会
 */
@RestController
@RequestMapping("/login")
public class LoginController {

	/**
	 * 获得去登陆的地址
	 * 
	 * @Title: code
	 * @Description: TODO
	 * @param @return 参数
	 * @return ApiResult<?> 返回类型
	 * @throws
	 * @author ZuiMeiHui.com 醉美会
	 */
	@GetMapping("/code")
	public ApiResult<?> to() {
		String url = Constants.CAS_OAUTH_SERVICE + "/authorize?response_type=code&client_id=" + Constants.CAS_CLIENT_ID
				+ "&redirect_uri=" + Constants.CAS_CLIENT_LOGIN + "/login/success/access_token";
		return ApiResult.success(url);
	}

	/**
	 * 获得access_token
	 * 
	 * @Title: token
	 * @Description: TODO
	 * @param @param  code
	 * @param @return 参数
	 * @return ApiResult<?> 返回类型
	 * @throws
	 * @author ZuiMeiHui.com 醉美会
	 */
	@GetMapping("/success/access_token")
	public ApiResult<?> accessToken(@RequestParam("code") String code) {
		String url = Constants.CAS_OAUTH_SERVICE + "/accessToken?grant_type=authorization_code&client_id="
				+ Constants.CAS_CLIENT_ID + "&client_secret=" + Constants.CAS_CLIENT_SECRET + "&redirect_uri="
				+ Constants.CAS_CLIENT_LOGIN + "/login/success/access_token&code=" + code;
		RestTemplateCustom restTemplateCustom = new RestTemplateCustom();
		ResponseEntity<String> response = restTemplateCustom.getForEntity(url, String.class);
		String responseBody = response.getBody();
		String accessToken = null;
		if (StringUtils.isNoneBlank(responseBody) && responseBody.indexOf("access_token") >= 0) {
			accessToken = responseBody.substring(responseBody.indexOf("access_token") + 13, responseBody.indexOf("&"));
		}
		return ApiResult.success(accessToken);
	}

	/**
	 * 获得用户ID
	 * 
	 * @Title: id
	 * @Description: TODO
	 * @param @param  accessToken
	 * @param @return 参数
	 * @return ApiResult<?> 返回类型
	 * @throws
	 * @author ZuiMeiHui.com 醉美会
	 */
	@GetMapping("/id")
	public ApiResult<?> id(@RequestHeader("token") String accessToken) {
		String url = Constants.CAS_OAUTH_SERVICE + "/profile?access_token=" + accessToken;
		RestTemplateCustom restTemplateCustom = new RestTemplateCustom();
		ResponseEntity<String> response = restTemplateCustom.getForEntity(url, String.class);
		String responseBody = response.getBody();
		JSONObject jsonObject = JSONObject.parseObject(responseBody);
		Map<String, String> map = new HashMap<String, String>();
		if (jsonObject != null) {
			map.put("id", jsonObject.getString("id"));
			map.put("clientId", jsonObject.getString("client_id"));
		}
		return ApiResult.success(map);
	}

	/**
	 * 获得退出登陆地址
	 * 
	 * @Title: logout
	 * @Description: TODO
	 * @param @return 参数
	 * @return ApiResult<?> 返回类型
	 * @throws
	 * @author ZuiMeiHui.com 醉美会
	 */
	@GetMapping("/logout")
	public ApiResult<?> logout() {
		String url = Constants.CAS_SERVICE + "/logout?service=" + Constants.CAS_CLIENT_LOGIN + "/login/code";
		return ApiResult.success(url);
	}
}
