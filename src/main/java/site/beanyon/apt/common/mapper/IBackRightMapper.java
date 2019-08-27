package site.beanyon.apt.common.mapper;

import site.beanyon.apt.common.entity.back.BackRight;

import java.util.List;

/**
 * 后端权限数据表映射接口，根据mybatis xml配置文件进行ORM映射
 * 表名：back_right
 *
 * @author BeanYon
 * 2019.08.01
 */
public interface IBackRightMapper {
    /**
     * 根据用户角色id获取权限（一个角色对应多个权限）
     *
     * @param rid 角色id
     * @return 该角色对应的权限列表
     */
    List<BackRight> findRightsByRid(Integer rid);

    /**
     * 根据用户id查询权限列表
     *
     * @param uid 用户id
     * @return 权限列表
     */
    List<BackRight> findRightsByUid(Integer uid);

    /**
     * 向数据库中插入后台权限
     *
     * @param backRight 后台权限实例
     * @return 影响的行数
     */
    Integer insertRight(BackRight backRight);
}
