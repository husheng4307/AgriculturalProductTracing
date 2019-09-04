package site.beanyon.apt.lhbb.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.beanyon.apt.lhbb.entity.DzDomain;
import site.beanyon.apt.lhbb.mapper.DzMapper;
import site.beanyon.apt.lhbb.service.DzService;
import java.util.Date;
import java.util.List;

/**
 * @Created by husheng
 * @on 19-8-28 上午11:54
 * @Version 1.0
 */
@Service
public class DzServiceImpl implements DzService {

    @Autowired
    private DzMapper dzMapper;

    /**
     * @Author: husheng
     * @param：  * @param 司机id，Integer id
     * @return： 司机用户实体，DzDomain
     * @Description:    通过档主id查询该档主信息
     * @Date: 上午11:39 19-9-4
    */
    @Override
    public DzDomain getDzById(Integer id) {
        DzDomain dz = dzMapper.getDzById(id);
        return dz;
    }


    /**
     * @Author: husheng
     * @param：  * @param int pageNum, int pageSize
     * @return：     档主信息列表，PageInfo<DzDomain>
     * @Description:    查询所有房主信息，并封装进PageInfo<>中，方便后续分页
     * @Date: 上午11:41 19-9-4
    */
    @Override
    public PageInfo<DzDomain> getDzAll(int pageNum, int pageSize) {

        //初始化分页器
        PageHelper.startPage(pageNum, pageSize);

        //查询结果
        List<DzDomain> dzAll = dzMapper.getDzAll();

        //封装进分页器
        PageInfo<DzDomain> pageInfo = new PageInfo<>(dzAll);

        //返回
        return pageInfo;
    }


    /**
     * @Author: husheng
     * @param：  * @param 档主类实体，DzDomain
     * @return：     boolean
     * @Description:    新增档主
     * @Date: 上午11:41 19-9-4
    */
    @Override
    public boolean insertDz(DzDomain dzDomain) {
        dzMapper.insertDz(dzDomain);
        return true;
    }


    /**
     * @Author: husheng
     * @param：  * @param 档主id，Integer id
     * @return：     boolean
     * @Description:    通过档主id号，删除该档主信息
     * @Date: 上午11:44 19-9-4
    */
    @Override
    public boolean deleteDzById(Integer id) {
        dzMapper.deleteDzById(id);
        return true;

    }

    /**
     * @Author: husheng
     * @param：  * @param 档主id，时间Date
     * @return：     boolean
     * @Description:    通过档主id，更新
     * @Date: 上午11:45 19-9-4
    */
    @Override
    public boolean updateDzById(Integer id, Date date) {
        dzMapper.updateDzById(id,new Date());
        return true;
    }
}
