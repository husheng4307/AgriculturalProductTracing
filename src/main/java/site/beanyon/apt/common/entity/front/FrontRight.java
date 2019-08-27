package site.beanyon.apt.common.entity.front;

import lombok.Data;

import java.util.Date;

/**
 * 前端权限实体
 *
 * @author BeanYon
 * 2019.07.30
 */
@Data
public class FrontRight {
    /**
     * 权限id，自增主键
     */
    private Integer id;
    /**
     * 权限名称，varchar(20)
     */
    private String name;
    /**
     * 权限描述，varchar(200)
     */
    private String description;
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
