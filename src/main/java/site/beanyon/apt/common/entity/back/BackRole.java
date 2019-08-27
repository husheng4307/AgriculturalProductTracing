package site.beanyon.apt.common.entity.back;

import lombok.Data;

import java.util.Date;

/**
 * 后端角色实体
 *
 * @author BeanYon
 * 2019.07.30
 */
@Data
public class BackRole {
    /**
     * 角色id，自增主键
     */
    private Integer id;
    /**
     * 父角色id，默认为0
     */
    private Integer pid;
    /**
     * 角色名称，varchar(20)
     */
    private String name;
    /**
     * 角色描述，varchar(200)
     */
    private String description;
    /**
     * 序号，排序依据
     */
    private Integer sort;
    /**
     * 创建时间，默认current_timestamp
     */
    private Date createTime;
    /**
     * 更新时间，默认current_timestamp
     */
    private Date updateTime;
    /**
     * 创建此条记录的用户id
     */
    private Integer createUser;
    /**
     * 更新此条记录的用户id
     */
    private Integer updateUser;
}
