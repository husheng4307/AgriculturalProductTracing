package site.beanyon.apt.lhbb.controller;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import site.beanyon.apt.common.entity.response.HttpResponse;
import site.beanyon.apt.common.entity.response.ResponseType;
import site.beanyon.apt.common.util.StringUtils;
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
    public HttpResponse getDzById(@ApiParam(value = "档主ID", required = true)
                                  @RequestParam("id") Integer id) {
        //调用Dzsercice，通过档主id进行查询
        DzDomain dzById = dzService.getDzById(id);

        //如果查询到的结果为空，则返回不存在
        if (dzById == null) {
            return new HttpResponse(ResponseType.NOTEXIST,null);
        }

        //向前段返回成功响应，并返回查询到的结果
        return new HttpResponse(ResponseType.SUCCESS,dzById);
    }

    @GetMapping("/find/all")
    @ApiOperation("查询所有档主")
    public HttpResponse getDzOfAll(@ApiParam(name = "pageNum", value = "页数", required = false) @RequestParam(name = "pageNum", required = false, defaultValue = "1")
                                                     int pageNum,
                                   @ApiParam(name = "pageSize", value = "每页条数", required = false) @RequestParam(name = "pageSize", required = false, defaultValue = "20")
                                                     int pageSize){

        //通过DzService，获取所有档主信息，并封装在PageInfo类中，方便后续的分页请求
        PageInfo<DzDomain> dzAll = dzService.getDzAll(pageNum, pageSize);

        //如果查询到的结果为空，则返回UNKNOWN
        if (dzAll == null){
            return new HttpResponse(ResponseType.UNKNOWN,new PageInfo<DzDomain>());
        }

        //向前段返回成功响应，并返回查询到的结果
        return new HttpResponse(ResponseType.SUCCESS,dzAll);
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation("删除档主，通过id")
    public HttpResponse deleteDzById(@ApiParam(value = "档主ID", required = true)
                                 @RequestParam("id") Integer id){

        //通过档主id，删除该档主信息
        boolean success = dzService.deleteDzById(id);

        //删除不成功，则向前端范湖ILLEGAL_DELETE非法删除
        if (!success) {
            return new HttpResponse(ResponseType.ILLEGAL_DELETE,null);
        }

        //向前端返回成功响应
        return new HttpResponse(ResponseType.SUCCESS,null);
    }

    @PutMapping("/update/{id}")
    @ApiOperation("更新档主，通过id")
    public HttpResponse updateDzById(@ApiParam(value = "档主ID", required = true) @RequestParam("id") Integer id){

        //如果档主id为空，则向前端返回，NOTHING_TO_UPDATE没有要更新的内容
        if (id == null) {
            return new HttpResponse(ResponseType.NOTHING_TO_UPDATE,null);
        }

        //通过档主id更新档主信息
        boolean success = dzService.updateDzById(id, new Date());

        //更新失败
        if (!success) {
            return new HttpResponse(ResponseType.FAILED, null);
        }

        //向前端返回成功响应
        return new HttpResponse(ResponseType.SUCCESS,null);
    }

    @PostMapping("/insert")
    @ApiOperation("新增档主")
    public HttpResponse insertDz(
            @ApiParam(value = "前端用户id", required = true) @RequestParam Integer DZ_QXID,
            @ApiParam(value = "档主名称", required = true) @RequestParam String DZ_MC,
            @ApiParam(value = "电话", required = false) @RequestParam(required = false) String DZ_DH,
            @ApiParam(value = "档口号", required = true) @RequestParam String DZ_DKH,
            @ApiParam(value = "区域", required = true) @RequestParam String DZ_QY,
            @ApiParam(value = "主营品种", required = true) @RequestParam String DZ_ZYPZID,
            @ApiParam(value = "档主身份证", required = true) @RequestParam String DZ_SFZ,
            @ApiParam(value = "营业执照", required = false) @RequestParam(required = false) String DZ_YYZZ,
            @ApiParam(value = "经营许可", required = false) @RequestParam(required = false) String DZ_JYXK,
            @ApiParam(value = "创建者id", required = true) @RequestParam Integer DZ_CJZID,
            @ApiParam(value = "更新者id", required = false) @RequestParam(required = false) Integer DZ_GXZID){

        //根据前端提交的参数，构造DzDomain实例
        DzDomain dzDomain = new DzDomain();

        //设置必须参数，如果其中每一项必须参数为空，则向前端返回，ILLEGAL_INPUT必要信息不完整
        if (DZ_QXID == null || StringUtils.isEmpty(DZ_MC) || StringUtils.isEmpty(DZ_DKH) || StringUtils.isEmpty(DZ_QY) || StringUtils.isEmpty(DZ_ZYPZID) || StringUtils.isEmpty(DZ_SFZ)){
            return new HttpResponse(ResponseType.ILLEGAL_INPUT,null);
        }else{
            //必要参数完整，则将参数封装进类
            dzDomain.setDZ_QXID(DZ_QXID);
            dzDomain.setDZ_MC(DZ_MC);
            dzDomain.setDZ_DKH(DZ_DKH);
            dzDomain.setDZ_QY(DZ_QY);
            dzDomain.setDZ_ZYPZID(DZ_ZYPZID);
            dzDomain.setDZ_SFZ(DZ_SFZ);
        }

        if (DZ_CJZID != null && DZ_CJZID > 0) {
            // 创建者id合法，设置
            dzDomain.setDZ_CJZID(DZ_CJZID);
            dzDomain.setDZ_GXZID(DZ_CJZID);
        } else {
            // 创建者id不合法，返回
            return new HttpResponse(ResponseType.ILLEGAL_CREATE_USER, null);
        }

        //非必要字段DZ_DH，如果不为空，则封装进类
        if(!StringUtils.isEmpty(DZ_DH)){
            dzDomain.setDZ_DH(DZ_DH);
        }

        //非必要字段DZ_YYZZ，如果不为空，则封装进类
        if(!StringUtils.isEmpty(DZ_YYZZ)){
            dzDomain.setDZ_DH(DZ_YYZZ);
        }

        //非必要字段DZ_JYXK，如果不为空，则封装进类
        if(!StringUtils.isEmpty(DZ_JYXK)){
            dzDomain.setDZ_DH(DZ_JYXK);
        }

        //设置创建时间以及更新时间
        Date date = new Date();
        dzDomain.setDZ_CJSJ(date);
        dzDomain.setDZ_GXSJ(date);

        boolean success = dzService.insertDz(dzDomain);

        //如果创建不成功，返回
        if (!success) {
            return new HttpResponse(ResponseType.UNKNOWN,null);
        }

        //向前端返回成功响应
        return new HttpResponse(ResponseType.SUCCESS,null);
    }
}
