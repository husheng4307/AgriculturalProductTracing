package site.beanyon.apt.common.security;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * Shiro安全管理框架的配置类
 *
 * @author BeanYon
 * 2019.07.22
 */
@Configuration
public class ShiroConfig {
    /**
     * 创建ShiroFilterFactoryBean
     *
     * @param defaultWebSecurityManager 默认的Shiro Web安全管理器
     * @return ShiroFilterFactoryBean 实例
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager defaultWebSecurityManager) {
        // 创建ShiroFilterFactoryBean实例
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        // 设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);

        // 设置过滤器
        Map<String, String> filterMap = new HashMap<>();
        // 添加后台用户，需要 back:user:add 权限
        filterMap.put("/back/user/create", "perms[back:user:create]");
        // 删除后台用户，需要 back:user:delete 权限
        filterMap.put("/back/user/delete", "perms[back:user:delete]");
        // 查询后台全部角色列表，需要 back:role:get 权限
        filterMap.put("/back/role/get", "perms[back:role:get]");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);

        // 修改默认登录接口
        shiroFilterFactoryBean.setLoginUrl("/back/no-login");

        // 修改默认未授权提示接口
        shiroFilterFactoryBean.setUnauthorizedUrl("/back/unauth");

        return shiroFilterFactoryBean;
    }

    /**
     * 创建默认的Shiro Web安全管理器
     *
     * @param userRealm 用户领域对象
     * @return DefaultWebSecurityManager 实例
     */
    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(UserRealm userRealm) {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(userRealm);
        defaultWebSecurityManager.setSessionManager(sessionManager());
        return defaultWebSecurityManager;
    }

    /**
     * 创建自定义的 ApiSessionManager
     *
     * @return ApiSessionManager实例
     */
    @Bean
    public SessionManager sessionManager() {
        return new ApiSessionManager();
    }

    /**
     * 凭证匹配器
     *
     * @return HashedCredentialsMatcher 实例
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        hashedCredentialsMatcher.setHashIterations(2);
        return hashedCredentialsMatcher;
    }

    /**
     * 创建用户领域对象
     *
     * @return UserRealm 实例
     */
    @Bean
    public UserRealm getUserRealm() {
        UserRealm userRealm = new UserRealm();
//        userRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return userRealm;
    }
}
