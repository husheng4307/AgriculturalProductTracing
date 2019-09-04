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
        return sj;
    }

    /**
     * @Author: husheng
     * @param：  * @param pageNum,pageSize
     * @return： * 用户实体列表PageInfo<SjDomain>
     * @Description:    查询所有司机用户
     * @Date: 下午9:00 19-8-27
     */
    @Override
    public PageInfo<SjDomain> getSjAll(int pageNum, int pageSize) {

        //初始化分页器
        PageHelper.startPage(pageNum, pageSize);

        //查询结果
        List<SjDomain> sjAll = sjMapper.getSjAll();

        //将结果封装进分页器
        PageInfo<SjDomain> pageInfo = new PageInfo<>(sjAll);

        //返回
        System.out.println(pageInfo);
        return pageInfo;
    }

    /**
     * @Author: husheng
     * @param：  * 司机表全字段
     * @Return: * boolean
     * @Description:    新增司机用户
     * @Date: 下午9:00 19-9-4
     */
    @Override
    public boolean insertSj(SjDomain sjDomain) {
        sjMapper.insertSj(sjDomain);
        return true;
    }

    /**
     * @Author: husheng
     * @param：  * 司机id，Integer id
     * @return：     boolean
     * @Description:    删除司机，通过匹配id
     * @Date: 上午10:42 19-9-4
     */
    @Override
    public boolean deleteSjById(Integer id) {
        sjMapper.deleteSjById(id);
        return true;
    }

    /**
     * @Author: husheng
     * @param：  * 司机id，Integer id，更新时间Date date
     * @return：     boolean
     * @Description:    更新司机
     * @Date: 上午10:42 19-9-4
     */
    @Override
    public boolean updateSjById(Integer id, Date date) {
        sjMapper.updateSjById(id,date);
        return true;
    }
}
