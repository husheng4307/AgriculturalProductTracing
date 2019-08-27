package site.beanyon.apt.common.mapper;

import site.beanyon.apt.common.entity.front.FrontRight;
import java.util.List;

/**
 * 前端权限数据表映射接口，根据mybatis xml配置文件进行ORM映射
 * 表名：front_right
 *
 * @author BeanYon
 * 2019.07.22
 */
public interface IFrontRightMapper {
    /**
     * 根据用户角色id获取权限（一个角色对应多个权限）
     *
     * @param rid 角色id
     * @return 该角色对应的权限列表
     */
    List<FrontRight> findRightsByRid(Integer rid);
}
