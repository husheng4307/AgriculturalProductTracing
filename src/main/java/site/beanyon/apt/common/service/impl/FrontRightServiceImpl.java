package site.beanyon.apt.common.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.beanyon.apt.common.entity.front.FrontRight;
import site.beanyon.apt.common.mapper.IFrontRightMapper;
import site.beanyon.apt.common.service.IFrontRightService;
import java.util.List;

/**
 * 用户角色权限服务实现类，实现用户权限相关的操作
 *
 * @author BeanYon
 * 2019.07.22
 */
@Service
public class FrontRightServiceImpl implements IFrontRightService {
    @Autowired
    private IFrontRightMapper userRoleAuthMapper;

    /**
     * 根据角色id获取权限列表
     *
     * @param rid 角色id
     * @return 权限列表
     */
    @Override
    public List<FrontRight> getUserRoleAuthByRid(Integer rid) {
        return userRoleAuthMapper.findRightsByRid(rid);
    }
}
