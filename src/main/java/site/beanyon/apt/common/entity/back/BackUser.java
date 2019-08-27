package site.beanyon.apt.common.entity.back;

import lombok.Data;

import java.util.Date;

/**
 * 后端用户实体
 *
 * @author BeanYon
 * 2019.07.30
 */
@Data
public class BackUser {
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
     * 手机号
     */
    private String tel;
    /**
     * 电子邮箱
     */
    private String email;
    /**
     * 工号
     */
    private String workNum;
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
     * 创建人id
     */
    private Integer createUser;
    /**
     * 用户信息更新时间，默认current_timestamp
     */
    private Date updateTime;
    /**
     * 更新人id
     */
    private Integer updateUser;
    /**
     * 上次登录时间，默认current_timestamp
     */
    private Date lastLoginTime;
    /**
     * 登录时生成的SessionId
     */
    private String sessionId;
}
