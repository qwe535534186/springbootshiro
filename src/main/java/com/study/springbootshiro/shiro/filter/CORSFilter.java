package com.study.springbootshiro.shiro.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.web.servlet.OncePerRequestFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;

@Component
public class CORSFilter extends OncePerRequestFilter {
	@Override
	protected void doFilterInternal(ServletRequest servletRequest, ServletResponse servletResponse,
			FilterChain filterChain) throws ServletException, IOException {
		HttpServletResponse res = (HttpServletResponse) servletResponse;
		HttpServletRequest req = (HttpServletRequest) servletRequest;

		String[] allowDomains = { "http://127.0.0.1:5500", "http://192.168.17.241:8080", "http://localhost:5500" };
		Set allowOrigins = new HashSet(Arrays.asList(allowDomains));
		String originHeads = req.getHeader("Origin");
		if (allowOrigins.contains(originHeads)) {
			res.setHeader("Access-Control-Allow-Origin", originHeads);
		} else {
			res.setHeader("Access-Control-Allow-Origin", "*");
		}
		res.setContentType("text/html;charset=UTF-8");
		res.setHeader("Access-Control-Allow-Methods", "*");
		res.setHeader("Access-Control-Max-Age", "0");
		res.setHeader("Access-Control-Allow-Headers",
				"Origin, No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type, X-E4M-With,userId,token");
		res.setHeader("Access-Control-Allow-Credentials", "true");
		res.setHeader("XDomainRequestAllowed", "1");
		filterChain.doFilter(servletRequest, servletResponse);

	}

}
