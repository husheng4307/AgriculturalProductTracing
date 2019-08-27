package site.beanyon.apt.common.entity.front;

import lombok.Data;

import java.util.Date;

/**
 * 前端用户实体
 *
 * @author BeanYon
 * 2019.07.30
 */
@Data
public class FrontUser {
    /**
     * 用户id，自增主键
     */
    private Integer id;
    /**
     * 登录帐号，默认手机号
     */
    private String account;
    /**
     * 用户密码，varchar(128)
     */
    private String password;
    /**
     * 密码盐，varchar(128)
     */
    private String salt;
    /**
     * 用户名，varchar(20)，默认为account
     */
    private String name;
    /**
     * 头像URL，varchar(255)
     */
    private String avatar;
    /**
     * 帐号状态（字典）
     */
    private Byte status;
    /**
     * 性别（1：男，2：女，默认为NULL）
     */
    private Byte sex;
    /**
     * 生日，默认为NULL
     */
    private Date birthday;
    /**
     * 用户创建时间，默认current_timestamp
     */
    private Date createTime;
    /**
     * 用户信息更新时间，默认current_timestamp
     */
    private Date updateTime;
    /**
     * 上次登录时间，默认current_timestamp
     */
    private Date lastLoginTime;
    /**
     * 用户的SessionId
     */
    private String sessionId;
}
