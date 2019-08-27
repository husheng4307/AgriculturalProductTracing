package site.beanyon.apt.common.service;

import site.beanyon.apt.common.entity.back.BackUser;

import java.util.List;

/**
 * 后台用户相关服务接口
 *
 * @author BeanYon
 * 2019.07.31
 */
public interface IBackUserService {
    /**
     * 创建后台用户
     *
     * @param backUser 后台用户实例
     * @return 是否添加成功
     */
    boolean createUser(BackUser backUser);

    /**
     * 根据用户id删除后台用户
     *
     * @param userId 后台用户id
     * @return 是否删除成功
     */
    boolean deleteUserById(Integer userId);

    /**
     * 更新后台用户
     *
     * @param backUser 后台用户实例
     * @return 是否更新成功
     */
    boolean updateUser(BackUser backUser);

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
    BackUser getUserById(Integer id);

    /**
     * 根据用户account获取用户
     *
     * @param account 用户登录时使用的account
     * @return 用户实例
     */
    BackUser getUserByAccount(String account);

    /**
     * 根据用户名称模糊查询，获得一个用户列表
     *
     * @param name 用户名称
     * @return 用户列表
     */
    List<BackUser> getUsersByNameLike(String name);

    /**
     * 查询全部后台用户
     *
     * @return 后台用户列表
     */
    List<BackUser> getAllUsers();

    /**
     * 为后台用户添加角色
     *
     * @param userId 用户id
     * @param roleId 角色id
     * @return 是否添加成功
     */
    boolean addRole(Integer userId, Integer roleId);
}
