package pers.mtx.configuration;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pers.mtx.util.UserRealm;

import java.util.LinkedHashMap;
import java.util.Map;

/*
shiro的配置类
 */
@Configuration
public class shiroConfig {
    /*
    创建ShiroFilterFactoryBean
     */
    @Bean
    public ShiroFilterFactoryBean getshiroFilterFactoryBean(@Qualifier("defaultWebSecurityManager") DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);


        //添加Shiro内置过滤器
        /*
        Shiro内置过滤器,可以实现权限相关的拦截器
            常用过滤器
                anon : 无需认证即可访问
                authc ; 必须认证才可以访问
                user: 使用rememberme可以直接访问
                perms: 该资源得到权限才可以访问
                role: 该资源必须得到角色权限才可以访问
         */

        Map<String,String > filterMap = new LinkedHashMap<>();
//        filterMap.put("/add","authc");
//        filterMap.put("/update","authc");

        filterMap.put("/testThymeleaf","anon");
        //放行login
        filterMap.put("/login","anon");

//        授权过滤器
        filterMap.put("/add","perms[user:add]");
        filterMap.put("/*","authc");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);

        //修改跳转页面
        shiroFilterFactoryBean.setLoginUrl("/tologin");
        //设置未授权提示页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/unAuth");

        return shiroFilterFactoryBean;
    }

    /*
    创建defaultwebSercurityManager
     */
    @Bean(name = "defaultWebSecurityManager")
    public DefaultWebSecurityManager getdefaultWebSecurityManager(@Qualifier("userRealm") UserRealm realm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联Realm
        securityManager.setRealm(realm);

        return securityManager;
    }

    /*
    创建realm
     */
    @Bean(name = "userRealm")
    public UserRealm getRealm(){
        return new UserRealm();
    }
}
