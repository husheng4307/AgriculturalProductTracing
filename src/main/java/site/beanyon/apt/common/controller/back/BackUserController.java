package site.beanyon.apt.common.controller.back;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import site.beanyon.apt.common.entity.back.BackUser;
import site.beanyon.apt.common.entity.response.HttpResponse;
import site.beanyon.apt.common.entity.response.ResponseType;
import site.beanyon.apt.common.service.IBackUserService;
import site.beanyon.apt.common.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * 后台用户控制器
 *
 * @author BeanYon
 * 2019.08.01
 */
@RestController
@RequestMapping("/back")
@Api(tags = "后台管理-用户接口")
public class BackUserController {
    @Autowired
    private IBackUserService backUserService;

    @PostMapping("/user/create")
    @ApiOperation("创建后台用户")
    public HttpResponse addUser(@ApiParam(value = "帐号（默认手机号）", required = true) @RequestParam String account,
                                @ApiParam(value = "密码", required = true) @RequestParam String password,
                                @ApiParam(value = "名称", required = true) @RequestParam String name,
                                @ApiParam(value = "工号", required = true) @RequestParam String workNum,
                                @ApiParam(value = "创建者id", required = true) @RequestParam Integer createUser) {
        // 组装后台用户实例
        BackUser backUser = new BackUser();

        // 判断手机号是否合法
        if (!StringUtils.checkTel(account)) {
            return new HttpResponse(ResponseType.ILLEGAL_TEL, null);
        } else {
            backUser.setAccount(account);
        }

        // 校验密码是否合法
        if (!StringUtils.checkPassword(password)) {
            return new HttpResponse(ResponseType.ILLEGAL_PASSWORD, null);
        } else {
            backUser.setPassword(password);
        }

        // 组装其它属性
        backUser.setName(name);
        backUser.setWorkNum(workNum);
        backUser.setCreateUser(createUser);
        backUser.setSalt(StringUtils.getRandomString());

        // 向数据库中添加用户
        boolean success = backUserService.createUser(backUser);

        // 出现未知错误
        if (!success) {
            return new HttpResponse(ResponseType.UNKNOWN, null);
        }

        // 创建用户成功
        return new HttpResponse(ResponseType.SUCCESS, null);
    }

    @PostMapping("/user/delete")
    @ApiOperation("根据用户id删除后台用户")
    public HttpResponse deleteUserById(@ApiParam(value = "后台用户id", required = true) @RequestParam Integer userId) {
        boolean success = backUserService.deleteUserById(userId);

        // 出现未知错误
        if (!success) {
            return new HttpResponse(ResponseType.UNKNOWN, null);
        }

        // 删除用户成功
        return new HttpResponse(ResponseType.SUCCESS, null);
    }

    @PostMapping("/user/update")
    @ApiOperation("更新后台用户")
    public HttpResponse updateUser(@ApiParam(value = "用户id", required = true) @RequestParam Integer id,
                                   @ApiParam(value = "密码") @RequestParam(required = false) String password,
                                   @ApiParam(value = "名称") @RequestParam(required = false) String name,
                                   @ApiParam(value = "头像URL") @RequestParam(required = false) String avatar,
                                   @ApiParam(value = "电话号码") @RequestParam(required = false) String tel,
                                   @ApiParam(value = "电子邮箱") @RequestParam(required = false) String email,
                                   @ApiParam(value = "帐号状态") @RequestParam(required = false) Byte status,
                                   @ApiParam(value = "性别（1：男，2：女）") @RequestParam(required = false) Byte sex,
                                   @ApiParam(value = "生日（时间戳）") @RequestParam(required = false) Long birthday) {
        // 提交的内容均为空，无需更新
        if (StringUtils.isEmpty(password) && StringUtils.isEmpty(name)
                && StringUtils.isEmpty(avatar) && StringUtils.isEmpty(tel) && StringUtils.isEmpty(email)
                && status == null && sex == null && birthday == null) {
            return new HttpResponse(ResponseType.NOTHING_TO_UPDATE, null);
        }

        // 组装后台用户实例
        BackUser backUser = new BackUser();
        backUser.setId(id);

        // 当密码不空时，校验密码是否合法
        if (password != null && !StringUtils.checkPassword(password)) {
            return new HttpResponse(ResponseType.ILLEGAL_PASSWORD, null);
        } else {
            backUser.setPassword(password);
        }

        backUser.setName(name);
        backUser.setAvatar(avatar);

        // 当手机号不空时，判断手机号是否合法
        if (tel != null && !StringUtils.checkTel(tel)) {
            return new HttpResponse(ResponseType.ILLEGAL_TEL, null);
        } else {
            backUser.setTel(tel);
        }

        // 当邮箱不空时，判断邮箱是否合法
        if (email != null && !StringUtils.checkEmail(email)) {
            return new HttpResponse(ResponseType.ILLEGAL_EMAIL, null);
        } else {
            backUser.setEmail(email);
        }

        backUser.setStatus(status);
        backUser.setSex(sex);

        // 防止Date抛出空指针异常
        if (birthday != null) {
            backUser.setBirthday(new Date(birthday));
        }

        // 获取当前操作用户，并设置更新者id
        BackUser currentUser = (BackUser) SecurityUtils.getSubject().getPrincipal();
        if (currentUser == null) {
            return new HttpResponse(ResponseType.NO_LOGIN, null);
        } else {
            backUser.setUpdateUser(currentUser.getId());
        }

        // 向数据库中更新用户
        boolean success = backUserService.updateUser(backUser);

        // 更新失败，可能是系统中不存在此用户
        if (!success) {
            return new HttpResponse(ResponseType.FAILED, null);
        }

        // 创建用户成功
        return new HttpResponse(ResponseType.SUCCESS, null);
    }

    @GetMapping("/user/get/all")
    @ApiOperation("查询全部后台用户")
    public HttpResponse getAllUsers() {
        List<BackUser> allUsers = backUserService.getAllUsers();
        // 查询用户成功
        return new HttpResponse(ResponseType.SUCCESS, allUsers);
    }

    @GetMapping("/user/get/id")
    @ApiOperation("根据id查询后台用户")
    public HttpResponse getUserById(@ApiParam(value = "后台用户id", required = true) @RequestParam Integer userId) {
        BackUser user = backUserService.getUserById(userId);
        // 查询用户成功
        return new HttpResponse(ResponseType.SUCCESS, user);
    }

    @GetMapping("/user/get/account")
    @ApiOperation("根据account查询后台用户")
    public HttpResponse getUserByAccount(@ApiParam(value = "后台用户Account", required = true) @RequestParam String account) {
        // 判断手机号是否合法
        if (!StringUtils.checkTel(account)) {
            return new HttpResponse(ResponseType.ILLEGAL_TEL, null);
        }

        BackUser user = backUserService.getUserByAccount(account);
        // 查询用户成功
        return new HttpResponse(ResponseType.SUCCESS, user);
    }

    @GetMapping("/user/get/name")
    @ApiOperation("根据用户name模糊查询后台用户")
    public HttpResponse getUserByNameLike(@ApiParam(value = "后台用户name", required = true) @RequestParam String name) {
        List<BackUser> userList = backUserService.getUsersByNameLike(name);
        // 查询用户成功
        return new HttpResponse(ResponseType.SUCCESS, userList);
    }
}
