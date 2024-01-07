package com.zuimeihui.demo.common.service;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zuimeihui.demo.common.constants.Constants;
import com.zuimeihui.demo.common.enums.BaseResultEnum;

import lombok.experimental.Accessors;

/**
 * 通用返回结果集
 * 
 * @ClassName: ApiResult
 * @Description: TODO
 * @author ZuiMeiHui.com 醉美会
 */
@Accessors(chain = true)
public class ApiResult<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer code;

	private String message;

	private T data;

	private ApiResult(Integer code, String message, T data) {
		this.code = code;
		this.message = message;
		this.data = data;
	}

	@JsonIgnore
	public Boolean isSuccess() {
		return code == BaseResultEnum.SUCCESS.getCode();
	}

	@JsonIgnore
	public Boolean isFail() {
		return code != BaseResultEnum.SUCCESS.getCode();
	}

	public Integer getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public T getData() {
		return data;
	}

	/**
	 * 返回成功数据 （status 200）
	 * 
	 * @Title: success
	 * @Description: TODO
	 * @param @param  <T>
	 * @param @param  data
	 * @param @return 参数
	 * @return ApiResult<T> 返回类型
	 * @throws
	 * @author ZuiMeiHui.com 醉美会
	 */
	public static <T> ApiResult<T> success(T data) {
		return new ApiResult<T>(BaseResultEnum.SUCCESS.getCode(), BaseResultEnum.SUCCESS.getValue(), data);
	}

	/**
	 * 返回成功数据 （status 200）
	 * 
	 * @Title: success
	 * @Description: TODO
	 * @param @param  <T>
	 * @param @param  msg
	 * @param @param  data
	 * @param @return 参数
	 * @return ApiResult<T> 返回类型
	 * @throws
	 * @author ZuiMeiHui.com 醉美会
	 */
	public static <T> ApiResult<T> success(String msg, T data) {
		return new ApiResult<T>(BaseResultEnum.SUCCESS.getCode(),
				msg != null ? msg.toString() : BaseResultEnum.SUCCESS.getValue(), data);
	}

	/**
	 * 返回成功数据（status 200）
	 * 
	 * @Title: success
	 * @Description: TODO
	 * @param @return 参数
	 * @return ApiResult<?> 返回类型
	 * @throws
	 * @author ZuiMeiHui.com 醉美会
	 */
	public static ApiResult<?> success() {
		return success(null);
	}

	/**
	 * 返回错误数据（status 500）
	 * 
	 * @Title: fail
	 * @Description: TODO
	 * @param @param  <T>
	 * @param @param  msg
	 * @param @return 参数
	 * @return ApiResult<T> 返回类型
	 * @throws
	 * @author ZuiMeiHui.com 醉美会
	 */
	public static <T> ApiResult<T> fail(String msg) {
		return new ApiResult<T>(BaseResultEnum.FAIL.getCode(),
				msg != null ? msg.toString() : BaseResultEnum.FAIL.getValue(), null);
	}

	/**
	 * 自定义返回错误数据
	 * 
	 * @Title: fail
	 * @Description: TODO
	 * @param @param  <T>
	 * @param @param  code
	 * @param @param  message
	 * @param @return 参数
	 * @return ApiResult<T> 返回类型
	 * @throws
	 * @author ZuiMeiHui.com 醉美会
	 */
	public static <T> ApiResult<T> fail(Integer code, String message) {
		return new ApiResult<T>(code, message, null);
	}

	/**
	 * 自定义返回错误数据
	 * 
	 * @Title: fail
	 * @Description: TODO
	 * @param @param  <T>
	 * @param @param  type
	 * @param @param  message
	 * @param @return 参数
	 * @return ApiResult<T> 返回类型
	 * @throws
	 * @author ZuiMeiHui.com 醉美会
	 */
	public static <T> ApiResult<T> fail(BaseResultEnum type, String message) {
		String s = StringUtils.isBlank(message) ? message : type.getValue() + "," + message;
		return new ApiResult<T>(type.getCode(), s, null);
	}

	/**
	 * 自定义返回错误数据
	 * 
	 * @Title: fail
	 * @Description: TODO
	 * @param @param  <T>
	 * @param @param  code
	 * @param @param  message
	 * @param @param  data
	 * @param @return 参数
	 * @return ApiResult<T> 返回类型
	 * @throws
	 * @author ZuiMeiHui.com 醉美会
	 */
	public static <T> ApiResult<T> fail(Integer code, String message, T data) {
		return new ApiResult<T>(code, message, data);
	}

	/**
	 * 分页
	 * 
	 * @Title: successPage
	 * @Description: TODO
	 * @param @param  pageMap
	 * @param @param  list
	 * @param @return 参数
	 * @return ApiResult<?> 返回类型
	 * @throws
	 * @author ZuiMeiHui.com 醉美会
	 */
	public static ApiResult<?> successPage(Map<String, ?> pageMap, List<?> list) {
		if (list == null) {
			list = Collections.emptyList();
		}
		Integer page = (Integer) pageMap.get("page");
		Integer pageSize = (Integer) pageMap.get("pageSize");
		if (page == null) {
			page = 1;
		}
		if (pageSize == null) {
			pageSize = Constants.PAGE_SIZE;
		}
		Integer begin = (page - 1) * pageSize;
		Integer end = page * pageSize - 1;
		List<?> dataList = null;
		if (list.size() <= 0 || list.size() < begin) {
			dataList = Collections.emptyList();
		} else if (begin < list.size() && list.size() <= end) {
			dataList = list.subList(begin, list.size());
		} else {
			dataList = list.subList(begin, end);
		}
		Map<String, Object> pageInfo = new HashMap<String, Object>();
		pageInfo.put("page", 0);
		pageInfo.put("pageSize", pageSize);
		pageInfo.put("total", list.size());
		pageInfo.put("pageCount", list.size() / pageSize);
		Map<String, Object> resultData = new HashMap<String, Object>();
		resultData.put("list", dataList);
		resultData.put("pageInfo", pageInfo);
		return success(resultData);
	}

}
