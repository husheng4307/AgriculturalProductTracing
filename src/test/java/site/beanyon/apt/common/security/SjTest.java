package site.beanyon.apt.common.security;

import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import site.beanyon.apt.lhbb.entity.SjDomain;
import site.beanyon.apt.lhbb.service.DzService;
import site.beanyon.apt.lhbb.service.SjService;

import java.util.Date;

/**
 * @Created by husheng
 * @on 19-8-28 上午10:31
 * @Version 1.0
 */

@SpringBootTest
@RunWith(SpringRunner.class)
public class SjTest {

    @Autowired
    private SjService sjService;

    @Autowired
    private DzService dzService;

    /**
     *  测试司机类的mapper相关操作
     * */
    @Test
    public void testSjMapper(){
//        SjDomain sjById = sjService.getSjById(1);
//        PageInfo<SjDomain> sjAll = sjService.getSjAll(1,1);
//        System.out.println(sjAll.getPages());
//        sjService.insertSj(11,22,"SSSS","sfz","jsz","cph");
//        sjService.insertSj((int)(Math.random()*100),(int)(Math.random()*100),"hs","13915468464","sfz","jsz","赣M22222",1,"dasdas;南昌市;大连市",new Date(),new Date());
//        sjService.deleteSjById(89);
        sjService.updateSjById(11,new Date());
    }

    @Test
    public void testDzMapper(){
//        dzService.insertDz(2,222,"hs01","12977778888","dangkouhao01","dong","pingguo;huasheng;niurou","sfz","yyzz","jyxk",new Date(),new Date(),1,1);
//        dzService.getDzById(1);
//        dzService.getDzAll(1,2);
//        dzService.updateDzById(1,new Date());
        dzService.deleteDzById(2);
    }
}
