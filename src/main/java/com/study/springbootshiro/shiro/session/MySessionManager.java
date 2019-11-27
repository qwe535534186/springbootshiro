package com.study.springbootshiro.shiro.session;

import java.io.Serializable;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.SerializeUtils;
import org.springframework.util.StringUtils;

/**
 * @author qy
 * @description 自定义session管理
 * @date 2019年11月12日 下午1:27:57
 */
public class MySessionManager extends DefaultWebSessionManager {

    private static final String AUTHORIZATION = "Authorization";

    private static final String REFERENCED_SESSION_ID_SOURCE = "Stateless request";

    public MySessionManager() {
        super();
    }

    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        /**
         *此方法获取客户端cookie的值,如果你的项目将sesssionId放在requestparam中，或者拼接在url中，请查看该方法源码，自行修改
         */
        String id = super.getSessionIdCookie().readValue(httpRequest,
                WebUtils.toHttp(response));
        if (id != null) {
            /**
             * 此处并非http 的session，是shiro在redis中缓存的session（SimpleSession） */
            /**
             * 此方法是查询redis中的session，笔者在sessionDao中注入了redisManager如果你重写了RedisSessionDAO，
             * 则需要更改获取session的方法
             */
            byte[] bs = redisManager.get((keyPrefix + id).getBytes());
            if (bs != null && bs.length > 0) {
                Session session = (Session) SerializeUtils.deserialize(bs);
                return session.getId();
            } else {
                return null;
            }
        }
        /** 如果redis中session未过期，此处必须调用父类获取方法 */
        return super.getSessionId(request, response);
    }

    private RedisManager redisManager = new RedisManager();

    private String keyPrefix = new MyRedisSessionDao().getKeyPrefix();

}
