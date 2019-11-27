package com.study.springbootshiro.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.study.springbootshiro.entity.SysUser;
import com.study.springbootshiro.exception.ExceptionEnum;
import com.study.springbootshiro.service.SysUserService;
import com.study.springbootshiro.shiro.config.ShiroConfig;
import com.study.springbootshiro.utils.Msg;
import com.study.springbootshiro.utils.RequestUtils;


/**
 * @author qy
 * @description 登录controller
 * @date 2019年11月12日 下午1:31:39
 */
@RestController
@RequestMapping(value = "/auth")
public class LoginController {

    @Autowired
    private SysUserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Msg submitLogin(String username, String password, boolean remember, HttpServletRequest request) {
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        token.setRememberMe(remember);
        Subject subject = SecurityUtils.getSubject();
        subject.login(token);
        SysUser user = (SysUser) subject.getPrincipal();
        // 执行到这里说明用户已登录成功
        return ExceptionEnum.success().add("user", user);

    }

    @RequestMapping(value = "/notLogin", method = RequestMethod.GET)
    public Msg notLogin() {
        return Msg.NOT_LOGGEDIN;
    }

    @RequestMapping(value = "/notRole", method = RequestMethod.GET)
    public Msg notRole() {
        return Msg.FORBIDDEN;
    }

    //	@RequiresPermissions("/users/update") // 权限管理;
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Msg update(SysUser user) {
        String salt = new SecureRandomNumberGenerator().nextBytes().toHex();
        SimpleHash hash = new SimpleHash(ShiroConfig.ALGORITHMNAME, user.getPassWord(), salt, ShiroConfig.ITERATIONS);
        String encodedPassword = hash.toHex();
        user.setPassWord(encodedPassword);
        user.setSalt(salt);
        userService.updateByPrimaryKeySelective(user);

        return ExceptionEnum.success();
    }

    //	@RequiresPermissions("/usersPage")
    @RequestMapping(value = "/print", method = RequestMethod.POST)
    public Msg print(String input) {
        return ExceptionEnum.success().add("data", input);
    }

    @RequestMapping(value = "/print1", method = RequestMethod.POST)
    public Msg print1(String input) {
        return ExceptionEnum.success().add("data1", input);
    }

    /**
     * 注销登录
     *
     * @return
     */
    @RequestMapping(value = "/logout")
    public Msg logout() {
        /** Shiro管理Session **/
        Subject sub = SecurityUtils.getSubject();
        sub.logout();

        return ExceptionEnum.success();
    }

    @RequestMapping(value = "getUserInfo", method = RequestMethod.POST)
    public Msg getUserInfo() {
        Subject sub = SecurityUtils.getSubject();
        Object principal = sub.getPrincipal();
        System.out.println(principal);
        return Msg.success().add("user", principal);
    }

    /**
     * 被踢出后跳转的页面
     * @return
     */
    @RequestMapping(value = "/kickout", method = RequestMethod.GET)
    public Msg kickOut() {
        return Msg.KIT_OUT;
    }
}