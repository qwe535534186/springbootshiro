package com.study.springbootshiro.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @description 返回json格式
 * @author qy
 * @date 2019年11月12日 下午1:44:34
 */
public class Msg {
	public static final Msg SYSTEM_ERROR = new Msg("10001", "系统异常");
	public static final Msg TOKEN_EXPIRED = new Msg("10002", "令牌过期");
	public static final Msg INVALID_REQUEST = new Msg("10003", "无效的请求");
	public static final Msg TOKEN_INEXISTENCE = new Msg("10004", "令牌不存在");
	public static final Msg ACCESS_DISABLE = new Msg("10005", "账户已被禁用");
	public static final Msg KIT_OUT = new Msg("10006", "用户在其他地方登陆");
	public static final Msg NOT_LOGGEDIN = new Msg("10007", "未登录,请先登录");
	public static final Msg ACCESS_ERROR = new Msg("10008", "账号或密码错误");
	public static final Msg FORBIDDEN = new Msg("403", "禁止访问");
	public static final Msg EXCESSIVEATTEMPTS = new Msg("10009", "尝试次数过多,请稍后重试");
	public static final Msg UNKNOWN_SESSION = new Msg("10010", "信息已经过期请,请重新登录");
	private String code;
	private String msg;
	
	
	private Map<String, Object> content = new HashMap<String, Object>();

	public Msg() {
		this.content.clear();
		this.code = null;
		this.code = null;
	}

	public Msg(String errorCode, String message) {
		this.code = errorCode;
		this.msg = message;
	}

	public Msg(String errorCode, String message, Object object) {
		this.code = errorCode;
		this.msg = message;
		this.content.put("data", object);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Map<String, Object> getContent() {
		return content;
	}

	public void setContent(Map<String, Object> content) {
		this.content = content;
	}

	public Msg add(String key, Object value) {
		this.getContent().put(key, value);
		return this;

	}

	public Msg addMap(Map<String, Object> map) {
		this.getContent().putAll(map);
		return this;

	}

	public static Msg success() {
		Msg result = new Msg();
		result.setCode("200");
		result.setMsg("成功");
		return result;
	}

	public static Msg fail() {
		Msg result = new Msg();
		result.setCode("220");
		result.setMsg("失败");
		return result;
	}

	public static Msg fail(String error) {
		Msg result = new Msg();
		result.setCode("220");
		result.setMsg(error);
		return result;
	}

	public static Msg judge(boolean bool) {
		if (bool) {
			return Msg.success();
		} else {
			return Msg.fail();
		}
	}

}
