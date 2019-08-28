package site.beanyon.apt.lhbb.service;

import com.github.pagehelper.PageInfo;
import site.beanyon.apt.lhbb.entity.DzDomain;

import java.util.Date;

/**
 * @Created by husheng
 * @on 19-8-28 上午11:50
 * @Version 1.0
 */
public interface DzService {

    /**
     * @Author: husheng
     * @param：  档主id，Integer id
     * @return： 档主实体，DzDomain
     * @Description: 通过档主id（DZ_ID）查询单个档主实体用户
     * @Date: 下午1:39 19-8-28
    */
    public DzDomain getDzById(Integer id);

    /**
     * @Author: husheng
     * @param：  * 第几页pageNum（默认第一页），每页条数pageSize
     * @return：     档主实体列表，List<DzDomain>
     * @Description:    查询所有档主用户
     * @Date: 下午1:39 19-8-28
    */
    public PageInfo<DzDomain> getDzAll(int pageNum, int pageSize);

    /**
     * @Author: husheng
     * @param：  * @param all
     * @return：     null
     * @Description:    新增档主
     * @Date: 下午1:41 19-8-28
    */
    public void insertDz(Integer id, Integer qxid, String mingcheng, String dianhua, String dangkouhao, String quyu,
                         String zypz, String sfz, String yyzz, String jyxk, Date cjsj, Date gxsj, Integer cjzid, Integer gxzid);

    /**
     * @Author: husheng
     * @param：  档主id
     * @return：     null
     * @Description: 通过档主id删除档主
     * @Date: 下午1:42 19-8-28
    */
    public void deleteDzById(Integer id);

    /**
     * @Author: husheng
     * @param：  档主id，更新时间Date
     * @return： null
     * @Description:    更新档主，并修改其修改时间
     * @Date: 下午1:42 19-8-28
    */
    public void updateDzById(Integer id, Date date);
}
