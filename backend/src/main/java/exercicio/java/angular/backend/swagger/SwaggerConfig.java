package exercicio.java.angular.backend.swagger;

import com.google.common.base.Predicates;
import io.swagger.annotations.SwaggerDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.function.Predicate;


@Configuration
@EnableSwagger2
public class SwaggerConfig {
@Bean
    public Docket api() {
        return  new Docket(DocumentationType.SWAGGER_2)
                .select().apis(Predicates.not(RequestHandlerSelectors.basePackage("org.springframework.boot"))).build();
    }

    public  void addResourceHandler(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
