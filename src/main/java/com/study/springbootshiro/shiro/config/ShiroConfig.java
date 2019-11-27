package com.study.springbootshiro.shiro.config;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.mgt.RememberMeManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.Cookie;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.study.springbootshiro.shiro.filter.CaptchaFormAuthenticationFilter;
import com.study.springbootshiro.shiro.realm.MyShiroRealm;
import com.study.springbootshiro.shiro.security.KickoutSessionControlFilter;
import com.study.springbootshiro.shiro.security.RetryLimitHashedCredentialsMatcher;
import com.study.springbootshiro.shiro.session.MyRedisSessionDao;
import com.study.springbootshiro.shiro.session.MySessionManager;

/**
 * @author qy
 * @description shiro 配置信息
 * @date 2019年11月12日 下午1:20:30
 */
@Configuration
public class ShiroConfig {

    public static final String ALGORITHMNAME = Md5Hash.ALGORITHM_NAME; // 散列算法:这里使用MD5算法;
    public static final Integer ITERATIONS = 2; //散列的次数，比如散列两次，相当于 md5(md5(""));
    public static final String REMEMBERTOKEN = "TOKEN";
    public static final String STRINGNULL = "";

    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // 没有登陆的用户只能访问登陆页面
        shiroFilterFactoryBean.setLoginUrl("/auth/notLogin");
        // 登录成功后要跳转的链接
        // shiroFilterFactoryBean.setSuccessUrl("/auth/index");
        shiroFilterFactoryBean.setUnauthorizedUrl("/auth/notRole");
        // 自定义拦截器
        Map<String, Filter> filtersMap = new LinkedHashMap<String, Filter>();
        // 限制同一帐号同时在线的个数。
        filtersMap.put("kickout", kickoutSessionControlFilter());
        shiroFilterFactoryBean.setFilters(filtersMap);
        // 权限控制map.
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        filterChainDefinitionMap.put("/css/**", "anon");
        filterChainDefinitionMap.put("/js/**", "anon");
        filterChainDefinitionMap.put("/img/**", "anon");
        filterChainDefinitionMap.put("/auth/login", "anon");
        filterChainDefinitionMap.put("/auth/notLogin", "anon");
        filterChainDefinitionMap.put("/auth/notRole", "anon");
        filterChainDefinitionMap.put("/websocket/**", "anon");
        filterChainDefinitionMap.put("/auth/logout", "anon");
        filterChainDefinitionMap.put("/auth/kickout", "anon");
        filterChainDefinitionMap.put("/**", "user,kickout");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 设置realm.
        securityManager.setRealm(myShiroRealm());
        // 自定义缓存实现 使用redis
        securityManager.setCacheManager(cacheManager());
        // 自定义session管理 使用redis
        securityManager.setSessionManager(sessionManager());
        // 自定义cookie管理
        securityManager.setRememberMeManager(rememberMeManager());
        return securityManager;
    }

    /**
     * 身份认证realm; (这个需要自己写，账号密码校验；权限等)
     *
     * @return
     */
    @Bean
    public MyShiroRealm myShiroRealm() {
        MyShiroRealm myShiroRealm = new MyShiroRealm();
        myShiroRealm.setCredentialsMatcher(retryLimitHashedCredentialsMatcher());
        return myShiroRealm;
    }

    /**
     * 密码校验 交给shiro验证
     *
     * @return
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName(ALGORITHMNAME);// 散列算法:这里使用MD5算法;
        hashedCredentialsMatcher.setHashIterations(ITERATIONS);// 散列的次数，比如散列两次，相当于 md5(md5(""));
        return hashedCredentialsMatcher;
    }

    /**
     * 限制同一账号登录同时登录人数控制
     *
     * @return
     */
    @Bean
    public RetryLimitHashedCredentialsMatcher retryLimitHashedCredentialsMatcher() {
        RetryLimitHashedCredentialsMatcher retryLimitHashedCredentialsMatcher = new RetryLimitHashedCredentialsMatcher();
        retryLimitHashedCredentialsMatcher.setHashAlgorithmName(ALGORITHMNAME);
        retryLimitHashedCredentialsMatcher.setHashIterations(ITERATIONS);
        retryLimitHashedCredentialsMatcher.setCount(3);
        return retryLimitHashedCredentialsMatcher;
    }

    /**
     * cacheManager 缓存 redis实现 使用的是shiro-redis开源插件
     *
     * @return
     */
    public RedisCacheManager cacheManager() {
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        return redisCacheManager;
    }

    /**
     * 配置shiro redisManager 使用的是shiro-redis开源插件
     *
     * @return
     */
    public RedisManager redisManager() {
        RedisManager redisManager = new RedisManager();
        redisManager.setHost("127.0.0.1");
        redisManager.setPort(6379);
        redisManager.setExpire(86400);// 配置缓存过期时间 s
        redisManager.setTimeout(0);
        return redisManager;
    }

    /**
     * Session Manager 使用的是shiro-redis开源插件
     */
    @Bean
    public DefaultWebSessionManager sessionManager() {

        // DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        MySessionManager sessionManager = new MySessionManager();
        sessionManager.setSessionDAO(redisSessionDAO());
        sessionManager.setSessionValidationSchedulerEnabled(true);
        sessionManager.setGlobalSessionTimeout(3600000); //设置超时时间1小时 3600000
        sessionManager.setSessionIdCookie(sessionSimpleCookie());
        return sessionManager;
    }

    /**
     * Remember MeManager cookie管理对象
     */
    private RememberMeManager rememberMeManager() {
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberSimpleCookie());
        // rememberMe cookie加密的密钥
        cookieRememberMeManager.setCipherKey(Base64.decode("4AvVhmFLUs0KTA3Kprsdag=="));
        return cookieRememberMeManager;
    }

    public Cookie rememberSimpleCookie() {
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        simpleCookie.setDomain(STRINGNULL);
        simpleCookie.setHttpOnly(true);
        simpleCookie.setPath("/");
        simpleCookie.setMaxAge(2592000); // 30天
        return simpleCookie;
    }

    public Cookie sessionSimpleCookie() {
        SimpleCookie simpleCookie = new SimpleCookie("adminjsid");
        simpleCookie.setDomain(STRINGNULL);
        simpleCookie.setHttpOnly(true);
        simpleCookie.setPath("/");
        simpleCookie.setMaxAge(-1);
        return simpleCookie;
    }

    /**
     * RedisSessionDAO shiro sessionDao层的实现 通过redis 使用的是shiro-redis开源插件
     */
    @Bean
    public RedisSessionDAO redisSessionDAO() {
//		RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        MyRedisSessionDao redisSessionDAO = new MyRedisSessionDao();
        redisSessionDAO.setRedisManager(redisManager());
        return redisSessionDAO;
    }

    /**
     * 限制同一账号登录同时登录人数控制
     *
     * @return
     */
    @Bean
    public KickoutSessionControlFilter kickoutSessionControlFilter() {
        KickoutSessionControlFilter kickoutSessionControlFilter = new KickoutSessionControlFilter();
        kickoutSessionControlFilter.setCacheManager(cacheManager());
        kickoutSessionControlFilter.setSessionManager(sessionManager());
        kickoutSessionControlFilter.setKickoutAfter(false);
        kickoutSessionControlFilter.setMaxSession(1);
        kickoutSessionControlFilter.setKickoutUrl("/auth/kickout");
        return kickoutSessionControlFilter;
    }

    public CaptchaFormAuthenticationFilter captchaFormAuthenticationFilter() {
        CaptchaFormAuthenticationFilter captchaFormAuthenticationFilter = new CaptchaFormAuthenticationFilter();
        return captchaFormAuthenticationFilter;
    }

    /***
     * 授权所用配置
     *
     * @return
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }

    /***
     * 使授权注解起作用不如不想配置可以在pom文件中加入 <dependency>
     * <groupId>org.springframework.boot</groupId>
     * <artifactId>spring-boot-starter-aop</artifactId> </dependency>
     *
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    /**
     * Shiro生命周期处理器
     */
    @Bean
    public LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

}
