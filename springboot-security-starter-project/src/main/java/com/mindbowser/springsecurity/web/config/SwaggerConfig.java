package com.mindbowser.springsecurity.web.config;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig
{

	public static final String HEADER = "header";

	public static final String DEVELOPER_NAME = "Mindbowser Info Solutions Pvt. Ltd";
	public static final String DEVELOPER_URL = "https://www.mindbowser.com/";
	public static final String DEVELOPER_EMAIL = "contact@mindbowser.com";

	public static final String AUTHORIZATION = "Authorization";

	@Bean
	public Docket swaggerAPI()
	{
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.mindbowser.springsecurity.web.controller"))
				.build()
				.useDefaultResponseMessages(false)
				.securitySchemes(apiKeys())
				.securityContexts(Collections.singletonList(securityContext()))
				.apiInfo(metaData());
	}

	private ApiInfo metaData()
	{
		return new ApiInfoBuilder()
				.title("Spring Boot REST API")
				.description("\"Spring Boot REST API for Kroo \"")
				.version("1.0.0")
				.license("Apache License Version 2.0")
				.licenseUrl("https://www.apache.org/licenses/LICENSE-2.0\"")
				.contact(new Contact(DEVELOPER_NAME, DEVELOPER_URL, DEVELOPER_EMAIL))
				.build();
	}

	private List<SecurityScheme> apiKeys()
	{
		ArrayList<SecurityScheme> securitySchemes = new ArrayList<>();
		securitySchemes.add(new ApiKey(AUTHORIZATION, AUTHORIZATION, HEADER));
		return securitySchemes;
	}

	private SecurityContext securityContext()
	{
		return SecurityContext.builder()
				.securityReferences(defaultAuth())
				.forPaths(PathSelectors.any())
				.build();
	}

	private List<SecurityReference> defaultAuth()
	{

		List<SecurityReference> defaultAuthList = new ArrayList<>();
		AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
		AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
		authorizationScopes[0] = authorizationScope;
		defaultAuthList.add(new SecurityReference(AUTHORIZATION, authorizationScopes));
		return defaultAuthList;
	}
}
