package site.beanyon.apt.common.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.beanyon.apt.common.entity.front.FrontUser;
import site.beanyon.apt.common.mapper.IFrontUserMapper;
import site.beanyon.apt.common.service.IFrontUserService;

import java.util.List;

/**
 * 用户服务实现类，实现用户相关的行为
 *
 * @author BeanYon
 * 2019.07.22
 */
@Service
public class FrontUserServiceImpl implements IFrontUserService {
    @Autowired
    private IFrontUserMapper frontUserMapper;

    /**
     * 创建用户（注册）
     *
     * @param frontUser 前端用户实例
     * @return 是否创建成功
     */
    @Override
    public boolean createUser(FrontUser frontUser) {
        Integer rowCount = frontUserMapper.insertUser(frontUser);
        return rowCount == 1;
    }

    @Override
    public boolean deleteUserById(Integer userId) {
        return false;
    }

    @Override
    public boolean updateUser(FrontUser user) {
        return false;
    }

    /**
     * 更新登录时间
     *
     * @param uid 用户id
     * @return 是否更新成功
     */
    @Override
    public boolean updateLoginTime(Integer uid) {
        Integer rowCount = frontUserMapper.updateLoginTime(uid);
        return rowCount == 1;
    }

    /**
     * 根据用户id获取用户
     *
     * @param id 用户id
     * @return 用户实例
     */
    @Override
    public FrontUser getUserById(Integer id) {
        return frontUserMapper.findUserById(id);
    }

    /**
     * 根据用户account获取用户
     *
     * @param account 用户登录时使用的account
     * @return 用户实例
     */
    @Override
    public FrontUser getUserByAccount(String account) {
        return frontUserMapper.findUserByAccount(account);
    }

    @Override
    public List<FrontUser> getUsersByNameLike(String name) {
        return null;
    }

    @Override
    public List<FrontUser> getAllUsers() {
        return null;
    }

    @Override
    public boolean addRole(Integer userId, Integer roleId) {
        return false;
    }
}
