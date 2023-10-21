package tw.intelegence.ncsist.sstp.utils.swagger;

import io.swagger.v3.oas.annotations.OpenAPI31;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Operation;
//import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.parameters.Parameter;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springdoc.core.models.GroupedOpenApi;
//import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.method.HandlerMethod;

import java.util.List;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "SSTP API",
                version = "1.0",
                description = "Your API Description"
        ),
        servers = @Server(url = "http://localhost:8080") // Specify your server URL
)
public class Swagger3Config {

    //配置一個test-public組
    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("test-public")
                .packagesToScan("tw.intelegence.ncsist.sstp")
//                                .pathsToMatch("/material/**")
                .addOperationCustomizer(operationCustomizer())
                .build();
    }
    //配置其他组
    @Bean
    public GroupedOpenApi otherApi() {
        return GroupedOpenApi.builder()
                .group("test-other")
                .packagesToScan("tw.intelegence.ncsist.sstp")
//                                .pathsToMatch("/other/**")
                .build();
    }

    /**
     * 給所有@Operation註釋的interface添加一個tellerno請求head參數
     * @return
     * */
    @Bean
    public OperationCustomizer operationCustomizer () {
        return new OperationCustomizer() {

            @Override
            public Operation customize(Operation operation, HandlerMethod handlerMethod) {
                operation.addParametersItem(
                        new Parameter()
                                .in(ParameterIn.HEADER.toString())
                                .name("tellerno")
                                .description("登錄用戶帳號")
                                .schema(new StringSchema())
                                .required(false)
                );
                return operation;
            }
        };
    }

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new io.swagger.v3.oas.models.info.Info().title("Swagger3 test API")
                        .description("Swagger3 test sample application")
                        .version("v1.0.0")
                        .license(new License().name("Apache 2.0").url("https://springdoc.org")))
//                .servers(List.of(new Server().url("http://localhost:8080")))
                .externalDocs(new ExternalDocumentation()
                        .description("SpringShop Wiki Documentation")
                        .url("https://springshop.wiki.github.org/docs"));
    }

}
