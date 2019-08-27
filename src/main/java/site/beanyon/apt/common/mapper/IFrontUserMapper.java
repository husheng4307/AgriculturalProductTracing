package site.beanyon.apt.common.mapper;

import site.beanyon.apt.common.entity.front.FrontUser;

/**
 * 前端用户数据表映射接口，根据mybatis xml配置文件进行ORM映射
 * 表名：front_user
 *
 * @author BeanYon
 * 2019.07.22
 */
public interface IFrontUserMapper {
    /**
     * 插入用户
     *
     * @param frontUser 前端用户实例
     */
    Integer insertUser(FrontUser frontUser);

    /**
     * 根据用户id查询用户
     *
     * @param id 用户id
     * @return 用户实例
     */
    FrontUser findUserById(Integer id);

    /**
     * 根据用户account查询用户
     *
     * @param account 用户登录时使用的account
     * @return 用户实例
     */
    FrontUser findUserByAccount(String account);

    /**
     * 更新登录时间
     *
     * @param uid 用户id
     * @return 是否更新成功
     */
    Integer updateLoginTime(Integer uid);
}
