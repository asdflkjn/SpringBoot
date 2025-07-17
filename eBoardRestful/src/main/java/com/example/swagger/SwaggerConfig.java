package com.example.swagger;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@Component //메모리에 띄우는 거,  컴포넌트의 자식들: 컨트롤러, 서비스, 레포지토리
@OpenAPIDefinition(info=@Info(title="스웨거 제목", version="버전"))
public class SwaggerConfig {
	
	@Bean
	public GroupedOpenApi chatOpenApi() {
		String [] paths = {"/test/**", "/auth/**"};
	
		return GroupedOpenApi
				.builder()
				.group("API test") //그룹명
				.pathsToMatch(paths)
				.build();
	}
}
