package site.beanyon.apt.lhbb.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import site.beanyon.apt.lhbb.entity.DzDomain;
import site.beanyon.apt.lhbb.entity.SjDomain;

import java.util.Date;
import java.util.List;

/**
 *  司机数据表映射接口
 *  表名：GG_NCP_DZ
 *
 * @Created by husheng
 * @on 19-8-28 上午11:28
 * @Version 1.0
 */
public interface DzMapper {

    /**
     * @Author: husheng
     * @param：  档主id,Integer id
     * @return： 档主类型实体，DzDomain
     * @Description: 通过档主id（DZ_ID）查询单个档主实体用户
     * @Date: 下午1:32 19-8-28
    */
    @Select("select DZ_ID, DZ_QXID, DZ_MC, DZ_DH, DZ_DKH, DZ_QY, DZ_ZYPZID, DZ_SFZ, DZ_YYZZ, DZ_JYXK, DZ_CJSJ, DZ_GXSJ, DZ_CJZID, DZ_GXZID" +
            " from GG_NCP_DZ" +
            " where DZ_ID=#{id}")
    public DzDomain getDzById(Integer id);

    /**
     * @Author: husheng
     * @param： null
     * @return： 档主实体列表，List<DzDomain>
     * @Description:    查询所有的档主
     * @Date: 下午1:33 19-8-28
    */
    @Select("select DZ_ID, DZ_QXID, DZ_MC, DZ_DH, DZ_DKH, DZ_QY, DZ_ZYPZID, DZ_SFZ, DZ_YYZZ, DZ_JYXK, DZ_CJSJ, DZ_GXSJ," +
            " DZ_CJZID, DZ_GXZID" +
            " from GG_NCP_DZ")
    public List<DzDomain> getDzAll();

    /**
     * @Author: husheng
     * @param：  * @param DzDomain dzDomain
     * @return：     null
     * @Description:    新增档主用户
     * @Date: 下午1:34 19-9-3
    */
    @Insert("insert into" +
            " GG_NCP_DZ" +
            " (DZ_ID, DZ_QXID, DZ_MC, DZ_DH, DZ_DKH, DZ_QY, DZ_ZYPZID, DZ_SFZ, DZ_YYZZ, DZ_JYXK, DZ_CJSJ, DZ_GXSJ, DZ_CJZID, DZ_GXZID)" +
            " values" +
            " (#{DZ_ID}, #{DZ_QXID}, #{DZ_MC}, #{DZ_DH}, #{DZ_DKH}, #{DZ_QY}, #{DZ_ZYPZID}, #{DZ_SFZ}, #{DZ_YYZZ}, #{DZ_JYXK}, #{DZ_CJSJ}, #{DZ_GXSJ}, #{DZ_CJZID}, #{DZ_GXZID})")
    public void insertDz(DzDomain dzDomain);

    /**
     * @Author: husheng
     * @param：  档主id,Integer id
     * @return：     null
     * @Description:    通过档主id，删除该档主
     * @Date: 下午1:35 19-8-28
    */
    @Delete("delete from" +
            " GG_NCP_DZ" +
            " where DZ_ID=#{id}")
    public void deleteDzById(Integer id);

    /**
     * @Author: husheng
     * @param：  档主id，Integer id     更新时间，Date date
     * @return：  null
     * @Description:    更新档主，修改更新时间
     * @Date: 下午1:36 19-8-28
    */
    @Update("update" +
            " GG_NCP_DZ" +
            " set DZ_GXSJ=#{date}" +
            " where DZ_ID=#{id}")
    public void updateDzById(Integer id, Date date);
}
