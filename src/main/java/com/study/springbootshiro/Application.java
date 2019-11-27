package com.study.springbootshiro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.cors.CorsConfiguration;

import com.study.springbootshiro.exception.CtrlExceptionHandler;

@SpringBootApplication
public class Application{
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	// 注册统一异常处理bean
    @Bean
    public CtrlExceptionHandler myExceptionResolver() {
        return new CtrlExceptionHandler();
    }
    
//	/**
//	 * 跨域过滤器
//	 * 
//	 * @return
//	 */
//	private CorsConfiguration buildConfig() {
//		CorsConfiguration corsConfiguration = new CorsConfiguration();
//		corsConfiguration.addAllowedOrigin("*");
//		corsConfiguration.addAllowedHeader("*");
//		corsConfiguration.addAllowedMethod("*");
//		return corsConfiguration;
//	}
//	
//	@Bean
//	public CorsFilter corsFilter() {
//		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//		source.registerCorsConfiguration("/**", buildConfig());
//		return new CorsFilter(source);
//	}
	
}
