package site.beanyon.apt.common.mapper;

import site.beanyon.apt.common.entity.back.BackRole;

import java.util.List;

/**
 * 后台角色数据表映射接口，根据mybatis xml配置文件进行ORM映射
 * 表名：back_role
 *
 * @author BeanYon
 * 2019.07.31
 */
public interface IBackRoleMapper {
    /**
     * 插入后台角色
     *
     * @param backRole 后台角色实例
     * @return 数据库受影响的行数
     */
    int insertRole(BackRole backRole);

    /**
     * 根据用户id获取角色列表
     *
     * @param uid 用户id
     * @return 角色列表
     */
    List<BackRole> findRolesByUid(Integer uid);

    /**
     * 插入后台角色及权限关系到数据库
     *
     * @param roleId  角色id
     * @param rightId 权限id
     * @return 受影响的行数
     */
    Integer insertRoleRightRel(Integer roleId, Integer rightId);

    /**
     * 查询后台全部角色
     *
     * @return 角色列表
     */
    List<BackRole> findAllRoles();
}
