package site.beanyon.apt.common.service;

import site.beanyon.apt.common.entity.front.FrontRole;

import java.util.List;

/**
 * 前端角色服务接口，定义用户角色相关的操作
 *
 * @author BeanYon
 * 2019.07.22
 */
public interface IFrontRoleService {
    /**
     * 根据用户id获取用户角色实例
     *
     * @param uid 用户id
     * @return 用户角色实例列表
     */
    List<FrontRole> getRolesByUid(Integer uid);
}
