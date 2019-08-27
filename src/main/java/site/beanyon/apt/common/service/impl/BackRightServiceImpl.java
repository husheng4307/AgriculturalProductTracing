package site.beanyon.apt.common.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import site.beanyon.apt.common.entity.back.BackRight;
import site.beanyon.apt.common.mapper.IBackRightMapper;
import site.beanyon.apt.common.service.IBackRightService;

import java.util.List;

/**
 * 后端权限服务实现类，实现用户权限相关的操作
 *
 * @author BeanYon
 * 2019.08.01
 */
@Service
public class BackRightServiceImpl implements IBackRightService {
    @Autowired
    private IBackRightMapper backRightMapper;

    /**
     * 根据角色id获取权限列表
     *
     * @param rid 角色id
     * @return 权限列表
     */
    @Override
    public List<BackRight> getRightsByRid(Integer rid) {
        return backRightMapper.findRightsByRid(rid);
    }

    /**
     * 根据用户id获取权限列表
     *
     * @param uid 用户id
     * @return 权限列表
     */
    @Override
    public List<BackRight> getRightsByUid(Integer uid) {
        return backRightMapper.findRightsByUid(uid);
    }

    /**
     * 添加后台权限
     *
     * @param backRight 后台权限实例
     * @return 是否添加成功
     */
    @Override
    public boolean createRight(BackRight backRight){
        Integer rowCount = backRightMapper.insertRight(backRight);
        return rowCount == 1;
    }
}
