package site.beanyon.apt.lhbb.controller;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import site.beanyon.apt.lhbb.entity.SjDomain;
import site.beanyon.apt.lhbb.service.SjService;

import java.util.Date;

/**
 *  司机控制器
 *
 * @Created by husheng
 * @on 19-8-27 下午7:39
 * @Version 1.0
 */
@RestController
@RequestMapping("/sj")
@Api(tags = "来货报备-司机")
public class SjController {

    @Autowired
    private SjService sjService;

    @GetMapping("/find/{id}")
    @ApiOperation("查询司机，通过id")
    public SjDomain getSjById(@ApiParam(value = "司机ID", required = true)
                                  @RequestParam("id") Integer id) {
        SjDomain sjById = sjService.getSjById(id);
        return sjById;
    }

    @GetMapping("/find/all")
    @ApiOperation("查询所有司机")
    public PageInfo<SjDomain> getSjOfAll(@ApiParam(name = "pageNum", value = "页数", required = false)
                                         @RequestParam(name = "pageNum", required = false, defaultValue = "1")
                                                     int pageNum,
                                         @ApiParam(name = "pageSize", value = "每页条数", required = false)
                                         @RequestParam(name = "pageSize", required = false, defaultValue = "20")
                                                     int pageSize){
        return sjService.getSjAll(pageNum, pageSize);
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation("删除司机，通过id")
    public void deleteSjById(@ApiParam(value = "司机ID", required = true)
                                 @RequestParam("id") Integer id){
        sjService.deleteSjById(id);
    }

    @PutMapping("/update/{id}")
    @ApiOperation("更新司机，通过id")
    public void updateSjById(@ApiParam(value = "司机ID", required = true)
                                 @RequestParam("id") Integer id){
        sjService.updateSjById(id,new Date());
    }

    @PostMapping("/insert")
    @ApiOperation("新增司机")
    public void insertSj(){
        sjService.insertSj((int)(Math.random()*100),(int)(Math.random()*100),"hs","13915468464","sfz","jsz","赣M22222",1,"dasdas;南昌市;大连市",new Date(),new Date());
    }
}
