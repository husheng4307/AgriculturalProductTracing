package site.beanyon.apt.lhbb.service;


import com.github.pagehelper.PageInfo;
import site.beanyon.apt.lhbb.entity.SjDomain;

import java.util.Date;
import java.util.List;

/**
 * 司机服务接口，定义与司机相关的操作
 *
 * @Created by husheng
 * @on 19-8-27 下午6:43
 * @Version 1.0
 */


public interface SjService {

    /**
     * @Author: husheng
     * @param：  * 司机用户id，Integer id
     * @return： * 司机实体类，SjDomain
     * @Description:    通过司机id（SJB_ID）查询单个司机实体用户
     * @Date: 下午9:00 19-8-27
    */
    public SjDomain getSjById(Integer id);

    /**
     * @Author: husheng
     * @param：  * @param 第几页pageNum（默认第一页），每页条数pageSize
     * @return： * 用户实体列表（数量为pageSize）
     * @Description:    查询所有司机用户
     * @Date: 下午9:00 19-8-27
    */
    public PageInfo<SjDomain> getSjAll(int pageNum, int pageSize);

    /**
     * @Author: husheng
     * @param：  * 司机表全字段
     * @Return: * null
     * @Description:    新增司机用户
     * @Date: 下午9:00 19-8-27
    */
    public void insertSj(Integer id, Integer qxid, String mingcheng, String dianhua, String sfz, String jsz,
                         String cph, Integer cx, String dizhi, Date cjsj, Date gxsj);

    /**
     * @Author: husheng
     * @param：  * 司机id，Integer id
     * @return：     null
     * @Description:    删除司机，通过匹配id
     * @Date: 上午10:42 19-8-28
    */
    public void deleteSjById(Integer id);

    /**
     * @Author: husheng
     * @param：  * 司机id，更新时间date
     * @return： 司机类主体SjDomai
     * @Description: 通过id查找用户，并修改其修改时间字段
     * @Date: 上午10:56 19-8-28
     */
    public void updateSjById(Integer id,Date date);

}
