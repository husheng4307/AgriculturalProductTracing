package site.beanyon.apt.common.swagger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import site.beanyon.apt.common.security.ApiSessionManager;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * API 管理框架 Swagger 配置类
 *
 * @author BeanYon
 * 2019.07.29
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Value("${swagger.title}")
    private String title;

    @Value("${swagger.description}")
    private String description;

    @Value("${swagger.terms-of-service-url}")
    private String termsOfServiceUrl;

    @Value("${swagger.contact-name}")
    private String contactName;

    @Value("${swagger.contact-url}")
    private String contactUrl;

    @Value("${swagger.contact-email}")
    private String contactEmail;

    @Value("${swagger.version}")
    private String version;

    // 要扫描的包
    private final String basePackage = "site.beanyon.apt";

    /**
     * 创建API应用
     * apiInfo() 增加API相关信息
     * 通过select()函数返回一个ApiSelectorBuilder实例,用来控制哪些接口暴露给Swagger来展现，
     * 本例采用指定扫描的包路径来定义指定要建立API的目录。
     */
    @Bean
    public Docket createRestApi() {
        // 添加公共的可选字段Authorization，模拟header传参
        ParameterBuilder auth = new ParameterBuilder();
        List<Parameter> parameters = new ArrayList<>();
        auth.name(ApiSessionManager.AUTHORIZATION).description("存储sessionId")
                .modelRef(new ModelRef("string")).parameterType("header")
                .required(false).build();
        parameters.add(auth.build());

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(basePackage))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(parameters);
    }

    /**
     * 创建该API的基本信息（这些基本信息会展现在文档页面中）
     * 访问地址：http://项目实际地址/swagger-ui.html
     */
    @Bean
    public ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(title)
                .description(description)
                .termsOfServiceUrl(termsOfServiceUrl)
                .contact(new Contact(contactName, contactUrl, contactEmail))
                .version("1.0")
                .build();
    }
}
