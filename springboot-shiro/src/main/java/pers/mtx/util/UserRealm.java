package pers.mtx.util;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import pers.mtx.domain.User;
import pers.mtx.service.UserService;

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
        //给资源进行授权
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        //添加资源的授权字符串
//        info.addStringPermission("user:add");
        Subject subject = SecurityUtils.getSubject();
        System.out.println(subject.getPrincipal());
        User user = (User)subject.getPrincipal();//获取当前登录用户的user
        User dbuser = userService.findById(user.getId());

        info.addStringPermission(dbuser.getPerms());



        return info;
    }

    //注入业务
    @Autowired
    private UserService userService;
    /*
    执行认证逻辑
     */

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行认证逻辑");
//        //假设用户名与密码
//        String name = "mtx";
//        String password = "9908";




        //编写shiro的判断逻辑
        //1.判断用户名
        UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;

        User user = userService.findByName(token.getUsername());
        if (user==null) {
            //用户名不存在
            return null;//返回null shiro底层会抛出UnknownAccountException
        }
        //2.判断密码
        return new SimpleAuthenticationInfo(user,user.getPassword(),"");
        //第一个参数,需要返回给login方法的一些数值,也是getPrincipal强转的类型
        //第二个参数,密码
        //第三个参数,shiro的名字,留空
    }
}
