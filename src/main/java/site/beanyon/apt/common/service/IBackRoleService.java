package site.beanyon.apt.common.service;

import site.beanyon.apt.common.entity.back.BackRole;

import java.util.List;

/**
 * 后台角色相关服务接口
 *
 * @author BeanYon
 * 2019.07.31
 */
public interface IBackRoleService {
    /**
     * 添加后台角色
     *
     * @param backRole
     * @return 是否添加成功
     */
    boolean createRole(BackRole backRole);

    /**
     * 根据用户id获取角色列表
     *
     * @param uid 用户id
     * @return 用户角色列表
     */
    List<BackRole> getRolesByUid(Integer uid);

    /**
     * 为后台角色添加权限
     *
     * @param roleId  角色id
     * @param rightId 权限id
     * @return 是否添加成功
     */
    boolean addRight(Integer roleId, Integer rightId);

    /**
     * 查询后台全部角色
     *
     * @return 角色列表
     */
    List<BackRole> getAllRoles();
}
