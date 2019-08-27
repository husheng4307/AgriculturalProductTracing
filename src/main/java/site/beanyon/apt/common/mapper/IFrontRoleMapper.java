package site.beanyon.apt.common.mapper;

import site.beanyon.apt.common.entity.front.FrontRole;

import java.util.List;

/**
 * 前端角色数据表映射接口，根据mybatis xml配置文件进行ORM映射
 * 表名：front_role
 *
 * @author BeanYon
 * 2019.07.22
 */
public interface IFrontRoleMapper {
    /**
     * 根据用户id查询用户角色信息
     *
     * @param uid 用户id
     * @return 用户角色列表
     */
    List<FrontRole> findRolesByUid(Integer uid);
}
