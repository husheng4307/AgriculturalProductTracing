package site.beanyon.apt.lhbb.controller;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import site.beanyon.apt.lhbb.entity.DzDomain;
import site.beanyon.apt.lhbb.service.DzService;

import java.util.Date;

/**
 *  档主控制器
 *
 * @Created by husheng
 * @on 19-8-28 下午12:37
 * @Version 1.0
 */

@RestController
@RequestMapping("/dz")
@Api(tags = "来货报备-档主")
public class DzController {

    @Autowired
    private DzService dzService;

    @GetMapping("/find/{id}")
    @ApiOperation("查询档主，通过id")
    public DzDomain getDzById(@ApiParam(value = "档主ID", required = true)
                                  @RequestParam("id") Integer id) {
        DzDomain dzById = dzService.getDzById(id);
        return dzById;
    }

    @GetMapping("/find/all")
    @ApiOperation("查询所有档主")
    public PageInfo<DzDomain> getDzOfAll(@ApiParam(name = "pageNum", value = "页数", required = false)
                                             @RequestParam(name = "pageNum", required = false, defaultValue = "1")
                                                     int pageNum,
                                         @ApiParam(name = "pageSize", value = "每页条数", required = false)
                                             @RequestParam(name = "pageSize", required = false, defaultValue = "20")
                                                     int pageSize){
        return dzService.getDzAll(pageNum, pageSize);
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation("删除档主，通过id")
    public void deleteDzById(@ApiParam(value = "档主ID", required = true)
                                 @RequestParam("id") Integer id){
        dzService.deleteDzById(id);
    }

    @PutMapping("/update/{id}")
    @ApiOperation("更新档主，通过id")
    public void updateDzById(@ApiParam(value = "档主ID", required = true)
                                 @RequestParam("id") Integer id){
        dzService.updateDzById(id,new Date());
    }

    @PostMapping("/insert")
    @ApiOperation("新增档主")
    public void insertDz(){
        dzService.insertDz((int)(Math.random()*100),222,"hs01","12977778888","dangkouhao01","dong","pingguo;huasheng;niurou","sfz","yyzz","jyxk",new Date(),new Date(),1,1);
    }
}
