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

    @Override
    public DzDomain getDzById(Integer id) {
        DzDomain dz = dzMapper.getDzById(id);
        System.out.println(dz);
        return dz;
    }

    @Override
    public PageInfo<DzDomain> getDzAll(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<DzDomain> dzAll = dzMapper.getDzAll();
        PageInfo<DzDomain> pageInfo = new PageInfo<>(dzAll);
        System.out.println(pageInfo);
        return pageInfo;
    }

    @Override
    public void insertDz(Integer id, Integer qxid, String mingcheng, String dianhua, String dangkouhao, String quyu, String zypz, String sfz, String yyzz, String jyxk, Date cjsj, Date gxsj, Integer cjzid, Integer gxzid) {
        dzMapper.insertDz(id, qxid, mingcheng, dianhua, dangkouhao, quyu, zypz, sfz, yyzz, jyxk, cjsj, gxsj, cjzid, gxzid);
    }

    @Override
    public void deleteDzById(Integer id) {
        dzMapper.deleteDzById(id);
    }

    @Override
    public void updateDzById(Integer id, Date date) {
        dzMapper.updateDzById(id,new Date());
    }
}
