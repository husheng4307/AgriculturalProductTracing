package site.beanyon.apt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * 农产品溯源项目启动入口
 *
 * @author BeanYon
 * 2019.07.22
 */
@SpringBootApplication
@EnableAsync
@MapperScan("site.beanyon.apt.common.mapper")
public class AgriculturalProducTracingApplication {
    public static void main(String[] args) {
        SpringApplication.run(AgriculturalProducTracingApplication.class, args);
    }
}
