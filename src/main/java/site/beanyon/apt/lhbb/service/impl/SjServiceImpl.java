package site.beanyon.apt.lhbb.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.beanyon.apt.lhbb.entity.SjDomain;
import site.beanyon.apt.lhbb.mapper.SjMapper;
import site.beanyon.apt.lhbb.service.SjService;

import java.util.Date;
import java.util.List;

/**
 * 司机服务接口，定义与司机相关的操作
 *
 * @Created by husheng
 * @on 19-8-27 下午6:44
 * @Version 1.0
 */
@Service
public class SjServiceImpl implements SjService {

    @Autowired
    private SjMapper sjMapper;

    /**
     * @Author: husheng
     * @param：  * 司机用户id，Integer id
     * @return： * 司机实体类，SjDomain
     * @Description:    通过司机id（SJB_ID）查询单个司机实体用户
     * @Date: 下午9:00 19-8-27
     */
    @Override
    public SjDomain getSjById(Integer id) {
        SjDomain sj = sjMapper.getSjById(id);
        System.out.println(sj.toString());
        return sj;
    }

    /**
     * @Author: husheng
     * @param：  * @param null
     * @return： * 用户实体列表
     * @Description:    查询所有司机用户
     * @Date: 下午9:00 19-8-27
     */
    @Override
    public PageInfo<SjDomain> getSjAll(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<SjDomain> sjAll = sjMapper.getSjAll();
        PageInfo<SjDomain> pageInfo = new PageInfo<>(sjAll);
        System.out.println(pageInfo);
        return pageInfo;
    }

    /**
     * @Author: husheng
     * @param：  * 司机表全字段
     * @Return: * null
     * @Description:    新增司机用户
     * @Date: 下午9:00 19-8-27
     */
    @Override
    public void insertSj(Integer id, Integer qxid, String mingcheng, String dianhua, String sfz, String jsz, String cph, Integer cx, String dizhi, Date cjsj, Date gxsj) {
        sjMapper.insertSj(id, qxid, mingcheng, dianhua, sfz, jsz, cph, cx, dizhi, cjsj, gxsj);
    }

    /**
     * @Author: husheng
     * @param：  * 司机id，Integer id
     * @return：     null
     * @Description:    删除司机，通过匹配id
     * @Date: 上午10:42 19-8-28
     */
    @Override
    public void deleteSjById(Integer id) {
        sjMapper.deleteSjById(id);
    }

    @Override
    public void updateSjById(Integer id, Date date) {
        sjMapper.updateSjById(id,date);
    }



}
