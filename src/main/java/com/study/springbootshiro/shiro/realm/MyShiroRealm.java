package com.study.springbootshiro.shiro.realm;

import java.util.Set;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.study.springbootshiro.entity.SysUser;
import com.study.springbootshiro.service.SysRoleService;
import com.study.springbootshiro.service.SysUserService;


/**
 * @author qy
 * @description shiro 验证及授权
 * @date 2019年11月12日 下午1:21:23
 */
public class MyShiroRealm extends AuthorizingRealm {
    private static org.slf4j.Logger logger = LoggerFactory.getLogger(MyShiroRealm.class);

    // 如果项目中用到了事物，@Autowired注解会使事物失效，可以自己用get方法获取值
    @Autowired
    private SysRoleService roleService;
    @Autowired
    private SysUserService userService;

    /**
     * 认证信息.(身份验证) : Authentication 是用来验证用户身份
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken)
            throws AuthenticationException {
        logger.info("---------------- 执行 Shiro 凭证认证 ----------------------");
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        String name = token.getUsername();
        String password = String.valueOf(token.getPassword());
        SysUser user = new SysUser();
        user.setUserName(name);
        user.setPassWord(password);
        // 从数据库获取对应用户名密码的用户
        SysUser userList = userService.findByUserName(name);
        if (userList != null) {
            // 用户为禁用状态
            if (userList.getUserEnable() != 1) {
                throw new DisabledAccountException();
            }

            logger.info("---------------- Shiro 凭证认证成功 ----------------------");
            SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(userList,
                    userList.getPassWord(),
                    ByteSource.Util.bytes(userList.getSalt()),
                    getName()
            );
            return authenticationInfo;
        }
        throw new UnknownAccountException();
    }

    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        logger.info("---------------- 执行 Shiro 权限获取 ---------------------");
        Object principal = principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        if (principal instanceof SysUser) {
            SysUser userLogin = (SysUser) principal;
            Set<String> roles = roleService.findRoleNameByUserId(userLogin.getId());
            authorizationInfo.addRoles(roles);

            Set<String> permissions = userService.findPermissionsByUserId(userLogin.getId());
            authorizationInfo.addStringPermissions(permissions);
        }
        logger.info("---- 获取到以下权限 ----");
        System.out.println(authorizationInfo.getStringPermissions().toString());
        logger.info(authorizationInfo.getStringPermissions().toString());
        logger.info("---------------- Shiro 权限获取成功 ----------------------");
        return authorizationInfo;
    }

}
