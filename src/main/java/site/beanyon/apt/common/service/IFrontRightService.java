package site.beanyon.apt.common.service;

import site.beanyon.apt.common.entity.front.FrontRight;

import java.util.List;

/**
 * 前端权限服务接口，定义用户权限相关的操作
 *
 * @author BeanYon
 * 2019.07.22
 */
public interface IFrontRightService {
    /**
     * 根据角色id获取权限列表
     *
     * @param rid 角色id
     * @return 权限列表
     */
    List<FrontRight> getUserRoleAuthByRid(Integer rid);
}
