package com.study.springbootshiro.websocket.listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class RequestListener implements ServletRequestListener {

	@Override
    public void requestInitialized(ServletRequestEvent sre) {
		// 将所有request请求都携带上httpSession
		((HttpServletRequest) sre.getServletRequest()).getSession();

	}

	public RequestListener() {
	}

	@Override
    public void requestDestroyed(ServletRequestEvent arg0) {
	}

	@Autowired
	private RequestListener requestListener;

	@Bean
	public ServletListenerRegistrationBean<RequestListener> servletListenerRegistrationBean() {
		ServletListenerRegistrationBean<RequestListener> servletListenerRegistrationBean = new ServletListenerRegistrationBean<>();
		servletListenerRegistrationBean.setListener(requestListener);
		return servletListenerRegistrationBean;
	}

}
