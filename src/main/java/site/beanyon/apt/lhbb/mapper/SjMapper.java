package site.beanyon.apt.lhbb.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import site.beanyon.apt.lhbb.entity.SjDomain;

import java.util.Date;
import java.util.List;

/**
 * 司机数据表映射接口
 * 表名：GG_NCP_SJB
 *
 * @Created by husheng
 * @on 19-8-27 下午6:41
 * @Version 1.0
 */
public interface SjMapper {

    /**
     * @Author: husheng
     * @param：  * Integer id，司机id（SJB_ID）
     * @return： * SjDomain，司机用户实体
     * @Description:    通过司机id（SJB_ID）查询单个司机实体用户
     * @Date: 下午8:45 19-8-27
    */
    @Select("select" +
            " SJB_ID,SJB_QXID,SJB_MC,SJB_DH,SJB_SFZ,SJB_JSZ,SJB_CPH,SJB_CX,SJB_DZ,SJB_CJSJ,SJB_GXSJ" +
            " from GG_NCP_SJB" +
            " where (SJB_ID=#{id})")
    public SjDomain getSjById(Integer id);


    /**
     * @Author: husheng
     * @param：  * @param null
     * @return： * 司机实体列表，List<SjDomain>
     * @Description:    查询所有的司机用户
     * @Date: 下午8:47 19-8-27
    */
    @Select("select SJB_ID,SJB_QXID,SJB_MC,SJB_DH,SJB_SFZ,SJB_JSZ,SJB_CPH,SJB_CX,SJB_DZ,SJB_CJSJ,SJB_GXSJ" +
            " from GG_NCP_SJB")
    public List<SjDomain> getSjAll();

    /**
     * @Author: husheng
     * @param：  * @param SjDomain sjDomain
     * @return： * null
     * @Description: 新增司机用户
     * @Date:  19-9-4
    */
    @Insert("insert into" +
            " GG_NCP_SJB" +
            "(SJB_ID,SJB_QXID,SJB_MC,SJB_DH,SJB_SFZ,SJB_JSZ,SJB_CPH,SJB_CX,SJB_DZ,SJB_CJSJ,SJB_GXSJ)" +
            " values (#{SJB_ID},#{SJB_QXID},#{SJB_MC},#{SJB_DH},#{SJB_SFZ},#{SJB_JSZ},#{SJB_CPH},#{SJB_CX},#{SJB_DZ},#{SJB_CJSJ},#{SJB_GXSJ})")
    public void insertSj(SjDomain sjDomain);


    /**
     * @Author: husheng
     * @param：  * 司机id，Integer id
     * @return：     null
     * @Description:    删除司机，通过匹配id
     * @Date: 上午10:41 19-8-28
    */
    @Delete("delete from " +
            "GG_NCP_SJB " +
            "where SJB_ID=#{id}")
    public void deleteSjById(Integer id);


    /**
     * @Author: husheng
     * @param：  * 司机id，更新时间date
     * @return： null
     * @Description: 通过id查找用户，并修改其修改时间字段
     * @Date: 上午10:56 19-8-28
    */
    @Update("update GG_NCP_SJB " +
            "set " +
            "SJB_GXSJ=#{date} " +
            "where SJB_ID=#{id}")
    public void updateSjById(Integer id, Date date);
}
