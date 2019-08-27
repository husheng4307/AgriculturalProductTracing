package site.beanyon.apt.common.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.beanyon.apt.common.entity.front.FrontRole;
import site.beanyon.apt.common.mapper.IFrontRoleMapper;
import site.beanyon.apt.common.service.IFrontRoleService;

import java.util.List;

/**
 * 用户角色服务实现类，实现用户角色相关的操作
 *
 * @author BeanYon
 * 2019.07.22
 */
@Service
public class FrontRoleServiceImpl implements IFrontRoleService {
    @Autowired
    private IFrontRoleMapper userRoleMapper;

    /**
     * 根据用户id获取用户角色实例
     *
     * @param uid 用户id
     * @return 用户角色实例列表
     */
    @Override
    public List<FrontRole> getRolesByUid(Integer uid) {
        return userRoleMapper.findRolesByUid(uid);
    }
}
