package com.example.configuration;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import lombok.RequiredArgsConstructor;

/**
 * Swagger 설정
 */
@Configuration
@RequiredArgsConstructor
public class SwaggerConfiguration {
	
	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
				.info(new Info()
						.title("springdoc-openapi")
						.version("1.0")
						.description("springdoc-openapi swagger-ui 화면입니다."));
	}
	
	@Bean
	public GroupedOpenApi api() {
		String[] paths = {"/**"};
		String[] packagesToScan = {"com.example.mvc.controller"};
		return GroupedOpenApi.builder().group("springdoc-openapi")
				.pathsToMatch(paths)
				.packagesToScan(packagesToScan)
				.build();
	}

}
