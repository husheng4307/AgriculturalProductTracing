package site.beanyon.apt.lhbb.entity;

import lombok.Data;

import java.util.Date;

/**
 *  档主实体
 *
 * @Created by husheng
 * @on 19-8-28 上午11:20
 * @Version 1.0
 */

@Data
public class DzDomain {
    /**
     *  档主id，自增主键
     * */
    private Integer DZ_ID;
    /**
     *  权限id,关联的前端用户id
     * */
    private Integer DZ_QXID;
    /**
     *  档主名称，CHAR(5)
     * */
    private String DZ_MC;
    /**
     *  档主电话，VARCHAR(11)
     * */
    private String DZ_DH;
    /**
     *  档口号，VARCHAR(45)
     * */
    private String DZ_DKH;
    /**
     *  所属区域，VARCHAR(10)
     * */
    private String DZ_QY;
    /**
     *  主营品种id，VARCHAR(100)
     *  多个id使用英文分号分隔
     * */
    private String DZ_ZYPZID;
    /**
     *  身份证图片链接，VARCHAR(100)
     * */
    private String DZ_SFZ;
    /**
     *  营业执照图片链接，VARCHAR(100)
     * */
    private String DZ_YYZZ;
    /**
     *  食品经营许可证图片链接，VARCHAR(100)
     * */
    private String DZ_JYXK;
    /**
     *  创建时间，默认current_timestamp
     * */
    private Date DZ_CJSJ;
    /**
     *  更新时间，默认current_timestamp
     * */
    private Date DZ_GXSJ;
    /**
     *  创建者id
     * */
    private Integer DZ_CJZID;
    /**
     *  更新者id
     * */
    private Integer DZ_GXZID;
}
