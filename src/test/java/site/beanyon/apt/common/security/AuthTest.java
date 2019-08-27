package site.beanyon.apt.common.security;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import site.beanyon.apt.common.entity.back.BackRight;
import site.beanyon.apt.common.entity.back.BackRole;
import site.beanyon.apt.common.entity.back.BackUser;
import site.beanyon.apt.common.entity.front.FrontRight;
import site.beanyon.apt.common.entity.front.FrontRole;
import site.beanyon.apt.common.service.*;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthTest {

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

    /**
     * 根据前端用户id获取用户
     */
    @Test
    public void testGetFrontUserById() {
        frontUserService.getUserById(1);
    }

    /**
     * 根据前端用户account获取用户
     */
    @Test
    public void testGetFrontUserByAccount() {
        frontUserService.getUserByAccount("13007215142");
    }

    /**
     * 根据前端用户id获取角色
     */
    @Test
    public void testGetFrontRoleByUid() {
        List<FrontRole> roles = frontRoleService.getRolesByUid(3);
        roles.forEach(frontRole -> System.out.println(frontRole.toString()));
    }

    /**
     * 根据前端角色id获取权限
     */
    @Test
    public void testGetFrontRightByRid() {
        List<FrontRight> userRoleAuthByRid = frontRightService.getUserRoleAuthByRid(2);
        userRoleAuthByRid.forEach(System.out::println);
    }

    /**
     * 根据后端用户id获取用户
     */
    @Test
    public void testGetBackUserById() {
        BackUser user = backUserService.getUserById(3);
        System.out.println(user.toString());
    }

    /**
     * 根据后端用户account获取用户
     */
    @Test
    public void testGetBackUserByAccount() {
        BackUser user = backUserService.getUserByAccount("13007215142");
        System.out.println(user.toString());
    }

    /**
     * 根据后端用户account获取用户
     */
    @Test
    public void testGetBackUsersByNameLike() {
        List<BackUser> users = backUserService.getUsersByNameLike("Bean");
        users.forEach(backUser -> {
            System.out.println(backUser.toString());
        });
    }

    /**
     * 根据后端用户id获取角色
     */
    @Test
    public void testGetBackRoleByUid() {
        List<BackRole> roles = backRoleService.getRolesByUid(3);
        roles.forEach(backRole -> System.out.println(backRole.toString()));
    }

    /**
     * 根据后端角色id获取权限
     */
    @Test
    public void testGetBackRightByRid() {
        List<BackRight> rights = backRightService.getRightsByRid(1);
        rights.forEach(backRight -> System.out.println(backRight.toString()));
    }

    /**
     * 根据后端用户id删除用户
     */
    @Test
    public void testDeleteBackUserById() {
        boolean success = backUserService.deleteUserById(6);
        System.out.println("删除后台用户：" + success);
    }
}
