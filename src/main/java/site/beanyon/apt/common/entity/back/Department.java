package site.beanyon.apt.common.entity.back;

import lombok.Data;

import java.util.Date;

/**
 * 后端部门实体
 *
 * @author BeanYon
 * 2019.07.30
 */
@Data
public class Department {
    /**
     * 自增主键
     */
    private Integer id;
    /**
     * 父部门id
     */
    private Integer pid;
    /**
     * 部门名称，varchar(45)
     */
    private String name;
    /**
     * 描述，varchar(255)
     */
    private String description;
    /**
     * 序号，排序依据
     */
    private Integer sort;
    /**
     * 创建时间，datetime，默认为current_timestamp
     */
    private Date createTime;
    /**
     * 更新时间，datetime，默认为null
     */
    private Date updateTime;
    /**
     * 创建者id
     */
    private Integer createUser;
    /**
     * 更新者id
     */
    private Integer updateUser;
}
