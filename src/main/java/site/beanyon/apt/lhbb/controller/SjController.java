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
    public HttpResponse getSjById(@ApiParam(value = "司机ID", required = true)
                                  @RequestParam("id") Integer id) {


        //调用SjService，通过司机id进行查询
        SjDomain sjById = sjService.getSjById(id);

        //如果查询到的结果为空，则返回不存在
        if (sjById == null){
            return new HttpResponse(ResponseType.NOTEXIST,null);
        }

        //向前段返回成功响应，并返回查询到的结果
        return new HttpResponse(ResponseType.SUCCESS,sjById);
    }

    @GetMapping("/find/all")
    @ApiOperation("查询所有司机")
    public HttpResponse getSjOfAll(@ApiParam(name = "pageNum", value = "页数", required = false) @RequestParam(name = "pageNum", required = false, defaultValue = "1")
                                                     int pageNum,
                                   @ApiParam(name = "pageSize", value = "每页条数", required = false) @RequestParam(name = "pageSize", required = false, defaultValue = "20")
                                                     int pageSize){

        //获取所有司机信息，并封装在PageInfo类中，方便后续的分页请求
        PageInfo<SjDomain> sjAll = sjService.getSjAll(pageNum, pageSize);

        //如果查询到的结果为空，则返回UNKNOWN
        if (sjAll == null){
            return new HttpResponse(ResponseType.UNKNOWN,new PageInfo<SjDomain>());
        }

        //向前段返回成功响应，并返回查询到的结果
        return new HttpResponse(ResponseType.SUCCESS,sjAll);
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation("删除司机，通过id")
    public HttpResponse deleteSjById(@ApiParam(value = "司机ID", required = true)
                                 @RequestParam("id") Integer id){

        //通过司机id，删除该档主信息
        boolean success = sjService.deleteSjById(id);

        //删除不成功，则向前端范湖ILLEGAL_DELETE非法删除
        if (!success){
            return new HttpResponse(ResponseType.ILLEGAL_DELETE,null);
        }

        //向前端返回成功响应
        return new HttpResponse(ResponseType.SUCCESS,null);
    }

    @PutMapping("/update/{id}")
    @ApiOperation("更新司机，通过id")
    public HttpResponse updateSjById(@ApiParam(value = "司机ID", required = true)
                                 @RequestParam("id") Integer id){

        //如果司机id为空，则向前端返回，NOTHING_TO_UPDATE没有要更新的内容
        if (id == null) {
            return new HttpResponse(ResponseType.NOTHING_TO_UPDATE,null);
        }

        //通过档主id更新档主信息
        boolean success = sjService.updateSjById(id, new Date());

        //更新失败
        if (!success) {
            return new HttpResponse(ResponseType.FAILED, null);
        }

        //向前端返回成功响应
        return new HttpResponse(ResponseType.SUCCESS,null);
    }

    @PostMapping("/insert")
    @ApiOperation("新增司机")
    public HttpResponse insertSj(@ApiParam(value = "前端用户id", required = true) @RequestParam Integer SJB_QXID,
                                 @ApiParam(value = "司机姓名", required = true) @RequestParam String SJB_MC,
                                 @ApiParam(value = "电话", required = false) @RequestParam(required = false) String SJB_DH,
                                 @ApiParam(value = "身份证", required = true) @RequestParam String SJB_SFZ,
                                 @ApiParam(value = "驾驶证", required = true) @RequestParam String SJB_JSZ,
                                 @ApiParam(value = "车牌号", required = true) @RequestParam String SJB_CPH,
                                 @ApiParam(value = "车型", required = false) @RequestParam(required = false) Integer SJB_CX,
                                 @ApiParam(value = "地址", required = false) @RequestParam(required = false) String SJB_DZ){

        //根据前端提交的参数，构造SjDomain实例
        SjDomain sjDomain = new SjDomain();

        //设置必须参数，如果其中每一项必须参数为空，则向前端返回，ILLEGAL_INPUT必要信息不完整
        if (SJB_QXID == null || StringUtils.isEmpty(SJB_MC) || StringUtils.isEmpty(SJB_SFZ) || StringUtils.isEmpty(SJB_JSZ) || StringUtils.isEmpty(SJB_CPH)){
            return new HttpResponse(ResponseType.ILLEGAL_INPUT,null);
        }else{
            //必要参数完整，则将参数封装进类
            sjDomain.setSJB_QXID(SJB_QXID);
            sjDomain.setSJB_MC(SJB_MC);
            sjDomain.setSJB_SFZ(SJB_SFZ);
            sjDomain.setSJB_JSZ(SJB_JSZ);
            sjDomain.setSJB_CPH(SJB_CPH);
        }

        //非必要字段SJB_DH，如果不为空，则封装进类
        if (!StringUtils.isEmpty(SJB_DH)) {
            sjDomain.setSJB_DH(SJB_DH);
        }

        //非必要字段SJB_DZ，如果不为空，则封装进类
        if (!StringUtils.isEmpty(SJB_DZ)){
            sjDomain.setSJB_DZ(SJB_DZ);
        }

        //非必要字段SJB_CX，如果不为空，则封装进类
        if (SJB_CX != null && SJB_CX > 0){
            sjDomain.setSJB_CX(SJB_CX);
        }

        //设置创建时间以及更新时间
        Date date = new Date();
        sjDomain.setSJB_CJSJ(date);
        sjDomain.setSJB_GXSJ(date);

        boolean success = sjService.insertSj(sjDomain);

        //如果创建不成功，返回
        if (!success) {
            return new HttpResponse(ResponseType.UNKNOWN,null);
        }

        //向前端返回成功响应
        return new HttpResponse(ResponseType.SUCCESS,null);
    }
}
