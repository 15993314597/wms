package com.wms.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    /**
     * 创建API应用
     * apiInfo() 增加API相关信息
     * 通过select()函数返回一个ApiSelectorBuilder实例,用来控制哪些接口暴露给Swagger来展现，
     * 本例采用指定扫描的包路径来定义指定要建立API的目录。
     *
     * @return
     */

    @Bean
    public Docket docket(Environment environment) {
        // 设置要显示的swagger环境
        Profiles profiles = Profiles.of("dev","test");
        // 通过environment.acceptsProfiles判断是否处在自己设定的环境当中
        boolean flag = environment.acceptsProfiles(profiles);
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .enable(flag)
                .select()   // RequestHandlerSelectors 配置要扫描接口的方式
                .apis(RequestHandlerSelectors.basePackage("com.wms.app.controller")) // 指定要扫描的包
                .paths(PathSelectors.any())
                .build();
    }

    // 配置Swagger信息 = apiInfo
    private ApiInfo apiInfo() {
        Contact contact = new Contact("玩一玩","https://www.baidu.com/","3067063725@qq.com");
        return new ApiInfo("wms系统的接口文档",
                "good good study, day day up",
                "1.0",
                "urn:tos",
                 contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList<VendorExtension>());

    }
}
