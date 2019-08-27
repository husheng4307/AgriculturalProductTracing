package site.beanyon.apt.lhbb.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 来货报备系统首页控制器
 *
 * @author BeanYon
 * 2019.07.29
 */
@RestController("/front")
@Api(tags = "微信小程序-来货报备-首页")
public class HomeController {
    @GetMapping("/banner")
    @ApiOperation("获取Banner")
    public String getBanner() {
        return "getBanner";
    }
}
