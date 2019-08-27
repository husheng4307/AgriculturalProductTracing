package site.beanyon.apt.common.service;

import site.beanyon.apt.common.entity.back.BackRight;

import java.util.List;

/**
 * 后端权限服务接口，定义用户权限相关的操作
 *
 * @author BeanYon
 * 2019.08.01
 */
public interface IBackRightService {
    /**
     * 根据角色id获取权限列表
     *
     * @param rid 角色id
     * @return 权限列表
     */
    List<BackRight> getRightsByRid(Integer rid);

    /**
     * 根据用户id获取权限列表
     *
     * @param uid 用户id
     * @return 权限列表
     */
    List<BackRight> getRightsByUid(Integer uid);

    /**
     * 添加后台权限
     *
     * @param backRight 后台权限实例
     * @return 是否添加成功
     */
    boolean createRight(BackRight backRight);
}
