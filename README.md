# shiroLearn
开始学习shiro

1. shiro核心api   

    Subject： 是用户主体（把操作交给SecurityManager）

    SecurityManager：安全管理器（关联Realm）

    Realm：Shiro连接数据的桥梁

2. 要导入shiro-spring整合依赖包
3. 写Realm,其中包括shiro的逻辑,继承AuthorizingRealm类
    
    * 
3. 先写shiro配置类
    * 创建ShiroFilterFactoryBean
    * 创建defaultwebSercurityManager
    * 创建realm
    * 若要和thymeleaf标签连用还需配置配置ShiroDialect
