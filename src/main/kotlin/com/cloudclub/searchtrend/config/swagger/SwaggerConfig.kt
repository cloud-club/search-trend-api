package com.cloudclub.searchtrend.config.swagger

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.ResponseEntity
import org.springframework.http.server.ServerHttpRequest
import org.springframework.http.server.ServerHttpResponse
import org.springframework.web.server.ServerWebExchange
import org.springframework.web.server.WebSession
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import java.util.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * Swagger Config (v3)
 *
 * @see <a href="http://localhost:8080/swagger-ui/index.html">Swagger</a>
 **/
@Configuration
class SwaggerConfig {
    @Bean
    fun docket(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
            .enable(true)
            .useDefaultResponseMessages(false)
            .ignoredParameterTypes(
                WebSession::class.java,
                HttpServletRequest::class.java,
                HttpServletResponse::class.java,
                ServerHttpRequest::class.java,
                ServerHttpResponse::class.java,
                ServerWebExchange::class.java
            )
            .apiInfo(apiInfo())
            .genericModelSubstitutes(
                Optional::class.java,
                ResponseEntity::class.java
            )
            .select()
            .paths(PathSelectors.regex("/api/.*"))
            .build()
    }

    private fun apiInfo() = ApiInfoBuilder()
        .title("CloudClub Search Trend Api")
        .version("v1.0.0")
        .build()
}
