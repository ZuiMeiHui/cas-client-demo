package com.zuimeihui.demo.service.controller.login;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
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
@RequestMapping("/check")
public class CheckController {

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
}
