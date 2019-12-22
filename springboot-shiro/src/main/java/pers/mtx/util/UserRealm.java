package pers.mtx.util;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/*
自定义Realm
 */
public class UserRealm  extends AuthorizingRealm {
    /*
    执行授权逻辑
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行授权逻辑");
        return null;
    }
    /*
    执行认证逻辑
     */

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行认证逻辑");
        //假设用户名与密码
        String name = "mtx";
        String password = "9908";

        //编写shiro的判断逻辑
        //1.判断用户名
        UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;
        if (!token.getUsername().equals(name)) {
            //用户名不存在
            return null;//返回null shiro底层会抛出UnknownAccountException
        }
        //2.判断密码
        return new SimpleAuthenticationInfo("",password,"");
        //第一个参数,需要返回给login方法的一些数值
        //第二个参数,密码
        //第三个参数,shiro的名字,留空
    }
}
