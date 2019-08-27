package site.beanyon.apt.common.security;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import site.beanyon.apt.common.entity.back.BackRight;
import site.beanyon.apt.common.entity.back.BackUser;
import site.beanyon.apt.common.entity.front.FrontRole;
import site.beanyon.apt.common.entity.front.FrontUser;
import site.beanyon.apt.common.service.*;
import site.beanyon.apt.common.util.RedisUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义Shiro用户领域定义，实现认证、授权相关的业务
 *
 * @author BeanYon
 * 2019.07.22
 */
public class UserRealm extends AuthorizingRealm {
    /**
     * type和account之间的分隔符
     */
    public final static String DIVIDER = "-";

    /**
     * 后端用户在redis中的key
     */
    private final String REDIS_KEY_OF_BACK_USER = "backuser";

    /**
     * 前端用户在redis中的key
     */
    private final String REDIS_KEY_OF_FRONT_USER = "frontuser";

    @Autowired
    private IFrontUserService frontUserService;

    @Autowired
    private IFrontRoleService frontRoleService;

    @Autowired
    private IFrontRightService frontRightService;

    @Autowired
    private IBackUserService backUserService;

    @Autowired
    private IBackRoleService backRoleService;

    @Autowired
    private IBackRightService backRightService;

    @Autowired
    private RedisUtils redisUtils;

    /**
     * 执行认证逻辑
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 获取用户的登录帐号和密码
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;

        // 切割用户类型及account
        String[] typeAndAccount = token.getUsername().split("-");
        String accountType = typeAndAccount[0];
        String account = typeAndAccount[1];

        if ("back".equals(typeAndAccount[0])) {
            // 后台用户

            // 先从redis缓存中获取用户信息
            BackUser user = (BackUser) redisUtils.hget(REDIS_KEY_OF_BACK_USER, account);

            // 缓存中不存在此用户
            if (user == null) {
                System.out.println("缓存中不存在此用户，从数据库中获取");

                // 根据登录帐号，从数据库中读取用户信息
                user = backUserService.getUserByAccount(account);

                // 判断账号是否有效（用户是否存在）
                if (user == null) {
                    // Shiro底层会抛出UnknownAccountException
                    return null;
                } else {
                    // 将用户信息写入缓存
                    redisUtils.hset(REDIS_KEY_OF_BACK_USER, account, user);
                }
            } else {
                System.out.println("缓存中存在此用户");
            }

            // 第二个参数传入从数据库中获取的密码，Shiro会自动和用户输入的密码进行比对
            return new SimpleAuthenticationInfo(user, user.getPassword(), "");
        } else {
            // 前台用户
            // 根据登录帐号，从数据库中读取用户信息
            FrontUser user = frontUserService.getUserByAccount(account);

            // 判断账号是否有效（用户是否存在）
            if (user == null) {
                // Shiro底层会抛出UnknownAccountException
                return null;
            }

            // 第二个参数传入从数据库中获取的密码，Shiro会自动和用户输入的密码进行比对
            return new SimpleAuthenticationInfo(user, user.getPassword(), "");
        }
    }

    /**
     * 执行授权逻辑
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // 获取用户的角色及权限信息
        Subject subject = SecurityUtils.getSubject();

        // 判断是否为后端用户授权
        if (subject.getPrincipal() instanceof BackUser) {
            BackUser backUser = (BackUser) subject.getPrincipal();
            // 组装权限集合
            List<String> permissionList = new ArrayList<>();

            // 根据用户id获取其权限
            List<BackRight> backRights = backRightService.getRightsByUid(backUser.getId());
            backRights.forEach(backRight -> {
                // 组装权限列表
                permissionList.add(backRight.getName());
            });

            // 创建授权对象
            SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
            simpleAuthorizationInfo.addStringPermissions(permissionList);
            return simpleAuthorizationInfo;
        } else if (subject.getPrincipal() instanceof FrontUser) {
            // 判断是否为前端用户授权
            FrontUser user = (FrontUser) subject.getPrincipal();
            List<FrontRole> roles = frontRoleService.getRolesByUid(user.getId());
//            List<FrontRight> userRoleAuthList = frontRightService.getUserRoleAuthByRid(userRole.getId());
//
//            // 组装权限集合
//            List<String> permissionList = new ArrayList<>();
//            for (FrontRight userRoleAuth : userRoleAuthList) {
//                permissionList.add(userRoleAuth.getName());
//            }
//
//            // 创建授权对象
//            SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
//            simpleAuthorizationInfo.addStringPermissions(permissionList);
//
//            return simpleAuthorizationInfo;
        }

        return null;
    }
}
