package site.beanyon.apt.lhbb.entity;

import lombok.Data;

import java.util.Date;

/**
 * 司机实体
 *
 * @Created by husheng
 * @on 19-8-27 下午6:38
 * @Version 1.0
 */

@Data
public class SjDomain {

    /**
     *  司机id，自增主键
     * */
    private Integer SJB_ID;
    /**
     *  关联的前端用户id
     * */
    private Integer SJB_QXID;
    /**
     *  司机真实姓名，VARCHAR(5)
     * */
    private String SJB_MC;
    /**
     *  司机联系电话，VARCHAR(11)
     * */
    private String SJB_DH;
    /**
     *  身份证图片链接，VARCHAR(100)
     * */
    private String SJB_SFZ;
    /**
     *  驾驶证图片链接，VARCHAR(100)
     * */
    private String SJB_JSZ;
    /**
     *  车牌号，VARCHAR(10)
     * */
    private String SJB_CPH;
    /**
     *  车型（在字典表car_type中取值）
     * */
    private Integer SJB_CX;
    /**
     *  默认地址，TEXT
     *  多个使用英文分号分隔
     * */
    private String SJB_DZ;
    /**
     *  创建时间，默认current_timestamp
     * */
    private Date SJB_CJSJ;
    /**
     *  更新时间，默认current_timestamp
     * */
    private Date SJB_GXSJ;

}
