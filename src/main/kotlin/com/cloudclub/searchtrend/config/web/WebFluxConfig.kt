package com.cloudclub.searchtrend.config.web

import com.cloudclub.searchtrend.common.extension.isProd
import com.cloudclub.searchtrend.common.extension.mapper
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
import org.springframework.format.FormatterRegistry
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar
import org.springframework.http.codec.HttpMessageReader
import org.springframework.http.codec.ServerCodecConfigurer
import org.springframework.http.codec.json.Jackson2JsonDecoder
import org.springframework.http.codec.json.Jackson2JsonEncoder
import org.springframework.http.codec.multipart.DefaultPartHttpMessageReader
import org.springframework.http.codec.multipart.MultipartHttpMessageReader
import org.springframework.http.codec.multipart.Part
import org.springframework.util.MimeType
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.reactive.config.CorsRegistry
import org.springframework.web.reactive.config.ResourceHandlerRegistry
import org.springframework.web.reactive.config.WebFluxConfigurer
import org.springframework.web.reactive.result.method.annotation.ArgumentResolverConfigurer
import java.nio.charset.Charset

@Configuration
class WebFluxConfig(
    private val environment: Environment
) : WebFluxConfigurer {
    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
            .allowedOriginPatterns(CorsConfiguration.ALL)
            .allowedMethods(CorsConfiguration.ALL)
            .allowedHeaders(CorsConfiguration.ALL)
            .allowCredentials(true)
    }

    override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
        registry.apply {
            this.addResourceHandler("/swagger-ui.html**")
                .addResourceLocations("classpath:/META-INF/resources/")
            this.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/")

            if (!environment.isProd()) {
                this.addResourceHandler("/docs/**")
                    .addResourceLocations("classpath:/static/docs/")
            }
        }
    }

    override fun configureArgumentResolvers(configurer: ArgumentResolverConfigurer) {
        configureHttpMessageCodecs(ServerCodecConfigurer.create())
    }

    override fun configureHttpMessageCodecs(configurer: ServerCodecConfigurer) {
        val mimeTypes = arrayOf(
            MimeType("application", "json"),
            MimeType("application", "*+json"),
            MimeType("application", "json", Charset.forName("UTF-8"))
        )

        configurer.defaultCodecs().apply {
            this.jackson2JsonEncoder(Jackson2JsonEncoder(mapper, *mimeTypes))
            this.jackson2JsonDecoder(Jackson2JsonDecoder(mapper))
            this.configureDefaultCodec { codec: Any? ->
                if (codec is MultipartHttpMessageReader) {
                    val partReader: HttpMessageReader<Part> = codec.partReader
                    if (partReader is DefaultPartHttpMessageReader) {
                        partReader.setMaxHeadersSize(32 * 1024)
                    }
                }
            }
            this.maxInMemorySize(1024 * 1024)
        }
    }

    override fun addFormatters(registry: FormatterRegistry) {
        val registrar = DateTimeFormatterRegistrar()
        registrar.setUseIsoFormat(true)
        registrar.registerFormatters(registry)
        super.addFormatters(registry)
    }
}
