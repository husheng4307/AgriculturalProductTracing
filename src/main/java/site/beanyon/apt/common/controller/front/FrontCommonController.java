package site.beanyon.apt.common.controller.front;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import site.beanyon.apt.common.entity.front.FrontUser;
import site.beanyon.apt.common.entity.response.HttpResponse;
import site.beanyon.apt.common.entity.response.ResponseType;
import site.beanyon.apt.common.service.IFrontUserService;
import site.beanyon.apt.common.util.StringUtils;

/**
 * 公共的微信小程序 API 控制器
 *
 * @author BeanYon
 * 2019.07.29
 */
@RestController
@RequestMapping("/front")
@Api(tags = "微信小程序-公共接口")
public class FrontCommonController {
    /**
     * account前缀，用于区分登录时的帐号类型
     */
    private final static String ACCOUNT_PREFIX = "front-";

    @Autowired
    private IFrontUserService frontUserService;

    /**
     * 注册
     *
     * @param account  账号（默认为手机号）
     * @param password 密码（散列后的密码）
     */
    @PutMapping("/sign-in")
    @ApiOperation(value = "注册")
    public HttpResponse signin(@ApiParam("用户登录账号，默认为手机号") @RequestParam String account,
                               @ApiParam("用户密码，散列后的密码") @RequestParam String password) {
        // 校验手机号是否合法
        boolean isTelLegal = StringUtils.checkTel(account);
        if (!isTelLegal) {
            return new HttpResponse(ResponseType.ILLEGAL_TEL, null);
        }

        // 校验密码是否合法
        if (!StringUtils.checkPassword(password)) {
            return new HttpResponse(ResponseType.ILLEGAL_PASSWORD, null);
        }

        // 构造用户实例
        FrontUser frontUser = new FrontUser();
        frontUser.setAccount(account);
        frontUser.setPassword(password);
        frontUser.setSalt("111");

        boolean success = frontUserService.createUser(frontUser);
        // 出现未知错误
        if (!success) {
            return new HttpResponse(ResponseType.UNKNOWN, null);
        }

        // 创建成功
        return new HttpResponse(ResponseType.SUCCESS, frontUser);
    }

    /**
     * 登录
     *
     * @param account  账号（默认为手机号）
     * @param password 密码（散列后的密码）
     */
    @PostMapping("/login")
    @ApiOperation("登录")
    public HttpResponse login(@ApiParam("用户登录账号，默认为手机号") @RequestParam String account,
                              @ApiParam("用户密码，散列后的密码") @RequestParam String password) {
        // 使用Shiro进行登录
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(ACCOUNT_PREFIX + account, password);

        // 抛出异常表示认证失败，未抛出异常表示认证成功
        try {
            subject.login(token);
            // 认证成功
            FrontUser frontUser = (FrontUser) subject.getPrincipal();
            frontUser.setSessionId((String) subject.getSession().getId());
            // 更新用户登录时间
            frontUserService.updateLoginTime(frontUser.getId());
            return new HttpResponse(ResponseType.SUCCESS, frontUser);
        } catch (UnknownAccountException e) {
            // 帐号不存在
            return new HttpResponse(ResponseType.ILLEGAL_ACCOUNT, null);
        } catch (IncorrectCredentialsException e) {
            // 密码错误
            return new HttpResponse(ResponseType.PASSWORD_NOT_MATCH, null);
        }
    }
}
