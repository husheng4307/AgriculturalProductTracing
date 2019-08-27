package site.beanyon.apt.common.controller.back;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import site.beanyon.apt.common.entity.back.BackRight;
import site.beanyon.apt.common.entity.back.BackRole;
import site.beanyon.apt.common.entity.response.HttpResponse;
import site.beanyon.apt.common.entity.response.ResponseType;
import site.beanyon.apt.common.service.IBackRightService;
import site.beanyon.apt.common.service.IBackRoleService;
import site.beanyon.apt.common.service.IBackUserService;
import site.beanyon.apt.common.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 后台管理 权限相关API 控制器
 *
 * @author BeanYon
 * 2019.07.31
 */
@RestController
@RequestMapping("/back")
@Api(tags = "后台管理-后台权限")
public class BackAuthController {
    @Autowired
    private IBackRoleService backRoleService;

    @Autowired
    private IBackUserService backUserService;

    @Autowired
    private IBackRightService backRightService;

    @PostMapping("/role/create")
    @ApiOperation("创建后台用户角色")
    public HttpResponse createBackRole(@ApiParam(value = "角色名称", required = true) @RequestParam String name,
                                       @ApiParam(value = "创建者id", required = true) @RequestParam Integer createUser,
                                       @ApiParam("父角色id") @RequestParam(required = false) Integer pid,
                                       @ApiParam("描述") @RequestParam(required = false) String description,
                                       @ApiParam("序号（排序依据）") @RequestParam(required = false) Integer sort) {
        // 根据前端提交的参数，构造BackRole实例
        BackRole backRole = new BackRole();

        // 设置必需参数
        if (StringUtils.isEmpty(name)) {
            // 角色名为空，返回
            return new HttpResponse(ResponseType.ILLEGAL_NAME, null);
        } else {
            // 角色名不为空，设置
            backRole.setName(name);
        }

        if (createUser != null && createUser > 0) {
            // 创建者id合法，设置
            backRole.setCreateUser(createUser);
        } else {
            // 创建者id不合法，返回
            return new HttpResponse(ResponseType.ILLEGAL_CREATE_USER, null);
        }

        // 设置非必需参数
        if (pid != null && pid > 0) {
            backRole.setPid(pid);
        }

        if (!StringUtils.isEmpty(description)) {
            backRole.setDescription(description);
        }

        if (sort != null && sort > 0) {
            backRole.setSort(sort);
        }

        // 向数据库中添加角色
        boolean success = backRoleService.createRole(backRole);

        // 未抛出异常，但添加失败，出现未知错误
        if (!success) {
            return new HttpResponse(ResponseType.UNKNOWN, null);
        }

        // 向前端发送添加成功响应
        return new HttpResponse(ResponseType.SUCCESS, backRole);
    }

    @PostMapping("/right/create")
    @ApiOperation("创建后台用户权限")
    public HttpResponse addBackRight(@ApiParam(value = "权限名称", required = true) @RequestParam String name,
                                     @ApiParam("描述") @RequestParam(required = false) String description,
                                     @ApiParam(value = "创建者id", required = true) @RequestParam Integer createUser) {
        // 根据前端提交的参数，BackRight
        BackRight backRight = new BackRight();

        // 设置必需参数
        backRight.setName(name);

        if (createUser > 0) {
            // 创建者id合法，设置
            backRight.setCreateUser(createUser);
        } else {
            // 创建者id不合法，返回
            return new HttpResponse(ResponseType.ILLEGAL_CREATE_USER, null);
        }

        // 设置非必需参数
        if (!StringUtils.isEmpty(description)) {
            backRight.setDescription(description);
        }

        // 向数据库中添加权限
        boolean success = backRightService.createRight(backRight);

        // 未抛出异常，但添加失败，出现未知错误
        if (!success) {
            return new HttpResponse(ResponseType.UNKNOWN, null);
        }

        // 向前端发送添加成功响应
        return new HttpResponse(ResponseType.SUCCESS, backRight);
    }

    @PostMapping("/auth/add-role")
    @ApiOperation("为后台用户添加角色")
    public HttpResponse addBackRole(@ApiParam(value = "后台用户id", required = true) @RequestParam Integer userId,
                                    @ApiParam(value = "后台角色id", required = true) @RequestParam Integer roleId) {
        // 向数据库中添加角色
        boolean success = backUserService.addRole(userId, roleId);

        // 未抛出异常，但添加失败，出现未知错误
        if (!success) {
            return new HttpResponse(ResponseType.UNKNOWN, null);
        }

        // 向前端发送添加成功响应
        return new HttpResponse(ResponseType.SUCCESS, null);
    }

    @PostMapping("/auth/add-right")
    @ApiOperation("为后台角色添加权限")
    public HttpResponse addBackRight(@ApiParam(value = "后台角色id", required = true) @RequestParam Integer roleId,
                                     @ApiParam(value = "后台权限id", required = true) @RequestParam Integer rightId) {
        // 向数据库中添加角色和权限关系
        boolean success = backRoleService.addRight(roleId, rightId);

        // 未抛出异常，但添加失败，出现未知错误
        if (!success) {
            return new HttpResponse(ResponseType.UNKNOWN, null);
        }

        // 向前端发送添加成功响应
        return new HttpResponse(ResponseType.SUCCESS, null);
    }


    @GetMapping("/role/get")
    @ApiOperation("查询后台全部角色")
    public HttpResponse getBackRole() {
        List<BackRole> allRoles = backRoleService.getAllRoles();

        // 获取到的角色为空
        if (allRoles == null) {
            return new HttpResponse(ResponseType.UNKNOWN, new ArrayList<BackRole>());
        }

        // 向前端发送添加成功响应
        return new HttpResponse(ResponseType.SUCCESS, allRoles);
    }
}
