package site.beanyon.apt.common.mapper;

import site.beanyon.apt.common.entity.back.BackUser;

import java.util.Date;
import java.util.List;

/**
 * 后端用户数据表映射接口，根据mybatis xml配置文件进行ORM映射
 * 表名：back_user
 *
 * @author BeanYon
 * 2019.07.31
 */
public interface IBackUserMapper {
    /**
     * 插入用户
     *
     * @param backUser 后端用户实例
     */
    Integer insertUser(BackUser backUser);

    /**
     * 根据用户id查询用户
     *
     * @param id 用户id
     * @return 用户实例
     */
    BackUser findUserById(Integer id);

    /**
     * 根据用户account查询用户
     *
     * @param account 用户登录时使用的account
     * @return 用户实例
     */
    BackUser findUserByAccount(String account);

    /**
     * 向数据库中插入一条用户角色关系
     *
     * @param userId 用户id
     * @param roleId 角色id
     * @return 受影响的行数
     */
    Integer insertUserRoleRel(Integer userId, Integer roleId);

    /**
     * 根据用户id删除后台用户
     *
     * @param userId 后台用户id
     * @return 是否删除成功
     */
    Integer deleteUserById(Integer userId);

    /**
     * 查询全部后台用户
     *
     * @return 后台用户列表
     */
    List<BackUser> findAllUsers();

    /**
     * 根据用户名称模糊查询用户
     *
     * @param name 用户名称
     * @return 用户列表
     */
    List<BackUser> findUsersByNameLike(String name);

    /**
     * 更新后台用户
     *
     * @param backUser 后台用户实例
     * @return 受影响的行数
     */
    Integer updateUser(BackUser backUser);

    /**
     * 更新登录时间
     *
     * @param uid  用户id
     * @return 受影响的行数
     */
    Integer updateLoginTime(Integer uid);
}
