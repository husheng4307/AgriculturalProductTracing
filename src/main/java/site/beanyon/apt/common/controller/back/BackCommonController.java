package site.beanyon.apt.common.controller.back;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.catalina.security.SecurityUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import site.beanyon.apt.common.entity.back.BackUser;
import site.beanyon.apt.common.entity.response.HttpResponse;
import site.beanyon.apt.common.entity.response.ResponseType;
import site.beanyon.apt.common.service.IBackUserService;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 公共的后台管理系统控制器
 *
 * @author BeanYon
 * 2019.07.30
 */
@RestController
@RequestMapping("/back")
@Api(tags = "后台管理-公共接口")
public class BackCommonController {
    /**
     * account前缀，用于区分登录时的帐号类型
     */
    private final static String ACCOUNT_PREFIX = "back-";

    @Autowired
    private IBackUserService backUserService;

    @PostMapping("login")
    @ApiOperation("用户登录")
    public HttpResponse login(@ApiParam("登录帐号") @RequestParam String account,
                              @ApiParam("登录密码") @RequestParam String password) {
        // 使用Shiro进行认证
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(ACCOUNT_PREFIX + account, password);
        // 抛出异常表示认证失败，未抛出异常表示认证成功
        try {
            subject.login(token);
            // 认证成功
            BackUser user = (BackUser) subject.getPrincipal();
            user.setSessionId((String) subject.getSession().getId());
            // 更新用户登录时间
            backUserService.updateLoginTime(user.getId());
            return new HttpResponse(ResponseType.SUCCESS, user);
        } catch (UnknownAccountException e) {
            // 帐号不存在
            return new HttpResponse(ResponseType.ILLEGAL_ACCOUNT, null);
        } catch (IncorrectCredentialsException e) {
            // 密码错误
            return new HttpResponse(ResponseType.PASSWORD_NOT_MATCH, null);
        }
    }

    /**
     * 未授权默认接口
     */
    @GetMapping("/unauth")
    @ApiIgnore
    public HttpResponse unAuth() {
        return new HttpResponse(ResponseType.UNAUTH, null);
    }

    /**
     * 尚未登录默认接口
     */
    @GetMapping("/no-login")
    @ApiIgnore
    public HttpResponse noLogin() {
        return new HttpResponse(ResponseType.NO_LOGIN, null);
    }
}
