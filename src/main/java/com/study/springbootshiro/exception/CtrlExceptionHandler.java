package com.study.springbootshiro.exception;

import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.session.UnknownSessionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.study.springbootshiro.utils.Msg;

/**
 * @description 全局异常处理类
 * @author qy
 * @date 2019年11月12日 下午1:32:17
 */
@RestControllerAdvice
public class CtrlExceptionHandler {
	private static Logger logger = LoggerFactory.getLogger(CtrlExceptionHandler.class);

	// 拦截未授权页面
	@ResponseStatus(value = HttpStatus.FORBIDDEN)
	@ExceptionHandler(UnauthorizedException.class)
	public Msg handleException(UnauthorizedException e) {
		logger.debug(e.getMessage());
		return Msg.FORBIDDEN;
	}

	@ResponseStatus(value = HttpStatus.FORBIDDEN)
	@ExceptionHandler(AuthorizationException.class)
	public Msg handleException2(AuthorizationException e) {
		logger.debug(e.getMessage());
		return Msg.FORBIDDEN;
	}

	@ExceptionHandler(Exception.class)
	public Msg exceptionHandle(Exception e) {
		logger.debug(e.getMessage());
		System.out.println("========================全局异常捕捉==============");
		if (e instanceof UnknownAccountException) {
			return Msg.ACCESS_ERROR;
		}
		if (e instanceof IncorrectCredentialsException) {
			return Msg.ACCESS_ERROR;
		}
		if (e instanceof DisabledAccountException) {
			return Msg.ACCESS_DISABLE;
		}
		
		if(e instanceof ExcessiveAttemptsException) {
			return Msg.EXCESSIVEATTEMPTS;
		}
		if(e instanceof UnknownSessionException) {
			return Msg.UNKNOWN_SESSION;
		}
		e.printStackTrace();
		return Msg.SYSTEM_ERROR.add("error", e.getMessage());
	}

}
