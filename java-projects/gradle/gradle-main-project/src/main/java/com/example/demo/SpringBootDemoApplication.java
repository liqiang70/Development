package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import com.example.DemoLibrary;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@MapperScan("com.example.demo.dao") // 扫描dao
@SpringBootApplication
@EnableSwagger2
public class SpringBootDemoApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		DemoLibrary demoLibrary = new DemoLibrary();
		demoLibrary.someLibraryMethod();
		
		SpringApplication.run(SpringBootDemoApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(SpringBootDemoApplication.class);
	}

	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
				.apis(RequestHandlerSelectors.basePackage("com.example.demo")).paths(PathSelectors.any()).build();
	}

	private ApiInfo apiInfo() {
		Contact contact = new Contact("Richard", "http://baidu.com", "richard@tom.com");

		return new ApiInfoBuilder().title("服务:DEMO后台 APIs").description("服务:DEMO后台APIs")
				.termsOfServiceUrl("https://www.att.com/legal/terms.MessagesTermsofServiceandLicenseAgreement.html").contact(contact).version("1.1")
				.build();
	}

}
