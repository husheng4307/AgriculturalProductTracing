package site.beanyon.apt.common.service;

import site.beanyon.apt.common.entity.front.FrontUser;

import java.util.List;

/**
 * 前端用户服务接口，定义用户相关的行为接口
 *
 * @author BeanYon
 * 2019.07.22
 */
public interface IFrontUserService {
    /**
     * 创建用户（注册）
     *
     * @param frontUser 前端用户实例
     * @return 是否创建成功
     */
    boolean createUser(FrontUser frontUser);

    /**
     * 根据用户id删除用户
     *
     * @param userId 用户id
     * @return 是否删除成功
     */
    boolean deleteUserById(Integer userId);

    /**
     * 更新前端用户
     *
     * @param user 用户实例
     * @return 是否更新成功
     */
    boolean updateUser(FrontUser user);

    /**
     * 更新登录时间
     *
     * @param uid 用户id
     * @return 是否更新成功
     */
    boolean updateLoginTime(Integer uid);

    /**
     * 根据用户id获取用户
     *
     * @param id 用户id
     * @return 用户实例
     */
    FrontUser getUserById(Integer id);

    /**
     * 根据用户account获取用户
     *
     * @param account 用户登录时使用的account
     * @return 用户实例
     */
    FrontUser getUserByAccount(String account);

    /**
     * 根据用户名称模糊查询，获得一个用户列表
     *
     * @param name 用户名称
     * @return 用户列表
     */
    List<FrontUser> getUsersByNameLike(String name);

    /**
     * 查询全部前端用户
     *
     * @return 前端用户列表
     */
    List<FrontUser> getAllUsers();

    /**
     * 为前端用户添加角色
     *
     * @param userId 用户id
     * @param roleId 角色id
     * @return 是否添加成功
     */
    boolean addRole(Integer userId, Integer roleId);
}
