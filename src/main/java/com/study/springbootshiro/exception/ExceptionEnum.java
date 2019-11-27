package com.study.springbootshiro.exception;

import com.study.springbootshiro.utils.Msg;

@SuppressWarnings("ALL")
public enum ExceptionEnum {

	SUCCESS("200", "成功"), SYSTEM_ERROR("10001", "系统异常"), TOKEN_EXPIRED("10002", "令牌过期"), INVALID_REQUEST("10003",
			"无效的请求"), TOKEN_INEXISTENCE("10004", "令牌不存在"), ACCESS_DISABLE("10005", "账户已被禁用"), KIT_OUT("10006",
					"用户在其他地方登陆"), NOT_LOGGEDIN("10007", "未登录,请先登录"), ACCESS_ERROR("10008",
							"账号或密码错误"), FORBIDDEN("403", "禁止访问"), EXCESSIVEATTEMPTS("10009", "尝试次数过多,请稍后重试");

	private String code;

	private String value;

	private ExceptionEnum(String code, String value) {
		this.code = code;
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	public static Msg success() {
		return new Msg(SUCCESS.code,SUCCESS.value);
	}

}
