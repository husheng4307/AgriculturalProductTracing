package site.beanyon.apt.common.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.beanyon.apt.common.entity.back.BackUser;
import site.beanyon.apt.common.mapper.IBackUserMapper;
import site.beanyon.apt.common.service.IBackUserService;

import java.util.List;

/**
 * 后台用户相关服务实现类
 *
 * @author BeanYon
 * 2019.07.31
 */
@Service
public class BackUserServiceImpl implements IBackUserService {
    @Autowired
    private IBackUserMapper backUserMapper;

    /**
     * 添加后台用户
     *
     * @param backUser 后台用户实例
     * @return 是否添加成功
     */
    @Override
    public boolean createUser(BackUser backUser) {
        Integer rowCount = backUserMapper.insertUser(backUser);
        return rowCount == 1;
    }

    /**
     * 根据用户id删除后台用户
     *
     * @param userId 后台用户id
     * @return 是否删除成功
     */
    @Override
    public boolean deleteUserById(Integer userId) {
        Integer rowCount = backUserMapper.deleteUserById(userId);
        return rowCount == 1;
    }

    /**
     * 更新后台用户
     *
     * @param backUser 后台用户实例
     * @return 是否更新成功
     */
    @Override
    public boolean updateUser(BackUser backUser) {
        Integer rowCount = backUserMapper.updateUser(backUser);
        return rowCount == 1;
    }

    /**
     * 更新登录时间
     *
     * @param uid 用户id
     * @return 是否更新成功
     */
    @Override
    public boolean updateLoginTime(Integer uid) {
        Integer rowCount = backUserMapper.updateLoginTime(uid);
        return rowCount == 1;
    }

    /**
     * 根据用户id获取用户
     *
     * @param id 用户id
     * @return 用户实例
     */
    @Override
    public BackUser getUserById(Integer id) {
        return backUserMapper.findUserById(id);
    }

    /**
     * 根据用户account获取用户
     *
     * @param account 用户登录时使用的account
     * @return 用户实例
     */
    @Override
    public BackUser getUserByAccount(String account) {
        return backUserMapper.findUserByAccount(account);
    }

    /**
     * 根据用户名称模糊查询用户
     *
     * @param name 用户名称
     * @return 用户列表
     */
    @Override
    public List<BackUser> getUsersByNameLike(String name) {
        return backUserMapper.findUsersByNameLike(name);
    }

    /**
     * 为后台用户添加角色
     *
     * @param userId 用户id
     * @param roleId 角色id
     * @return 是否添加成功
     */
    @Override
    public boolean addRole(Integer userId, Integer roleId) {
        Integer rowCount = backUserMapper.insertUserRoleRel(userId, roleId);
        return rowCount == 1;
    }

    /**
     * 查询全部后台用户
     *
     * @return 后台用户列表
     */
    @Override
    public List<BackUser> getAllUsers() {
        return backUserMapper.findAllUsers();
    }
}
