package com.study.springbootshiro.shiro.filter;


import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @description 配合ajax 重写拦截器(废弃)
 * @author qy
 * @date 2019年11月12日 下午4:28:52
 */
public class CaptchaFormAuthenticationFilter extends FormAuthenticationFilter {

	private static final Logger log = LoggerFactory.getLogger(CaptchaFormAuthenticationFilter.class);

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		if (isLoginRequest(request, response)) {
			if (isLoginSubmission(request, response)) {
				if (log.isTraceEnabled()) {
					log.trace("Login submission detected.  Attempting to execute login.");
				}
				return executeLogin(request, response);
			} else {
				if (log.isTraceEnabled()) {
					log.trace("Login page view.");
				}
				return true;
			}
		} else {
			HttpServletRequest httpRequest = WebUtils.toHttp(request);

			if (isAjax(httpRequest)) {
				HttpServletResponse httpServletResponse = WebUtils.toHttp(response);
				httpServletResponse.sendError(401);
				return false;
			} else {
				if (log.isTraceEnabled()) {
					log.trace("Attempting to access a path which requires authentication.  Forwarding to the "
							+ "Authentication url [" + getLoginUrl() + "]");
				}
				saveRequestAndRedirectToLogin(request, response);
			}

			return false;
		}
	}

	/*
	 * 判断ajax请求
	 * 
	 * @param request
	 * 
	 * @return
	 */
	boolean isAjax(HttpServletRequest request) {
		return (request.getHeader("X-Requested-With") != null
				&& "XMLHttpRequest".equals(request.getHeader("X-Requested-With").toString()));
	}
	
	
}
