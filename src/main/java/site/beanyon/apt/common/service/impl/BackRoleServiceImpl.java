package site.beanyon.apt.common.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.beanyon.apt.common.entity.back.BackRole;
import site.beanyon.apt.common.mapper.IBackRoleMapper;
import site.beanyon.apt.common.service.IBackRoleService;

import java.util.List;

/**
 * 后台角色服务实例类
 *
 * @author BeanYon
 * 2019.07.31
 */
@Service
public class BackRoleServiceImpl implements IBackRoleService {
    @Autowired
    private IBackRoleMapper backRoleMapper;

    /**
     * 添加后台角色
     *
     * @param backRole 后台角色实例
     * @return 是否添加成功
     */
    @Override
    public boolean createRole(BackRole backRole) {
        int rowCount = backRoleMapper.insertRole(backRole);
        return rowCount == 1;
    }

    /**
     * 根据用户id查询角色列表
     *
     * @param uid 用户id
     * @return 角色列表
     */
    @Override
    public List<BackRole> getRolesByUid(Integer uid) {
        return backRoleMapper.findRolesByUid(uid);
    }

    /**
     * 为后台角色添加权限
     *
     * @param roleId  角色id
     * @param rightId 权限id
     * @return 是否添加成功
     */
    @Override
    public boolean addRight(Integer roleId, Integer rightId) {
        Integer rowCount = backRoleMapper.insertRoleRightRel(roleId, rightId);
        return rowCount == 1;
    }

    /**
     * 查询后台全部角色
     *
     * @return 角色列表
     */
    @Override
    public List<BackRole> getAllRoles() {
        return backRoleMapper.findAllRoles();
    }
}
