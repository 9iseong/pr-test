package com.proj.prserver.config

import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@EnableWebMvc
@Configuration
class WebConfig(
    val appProperties : AppProperties
): WebMvcConfigurer {

  override fun addCorsMappings(registry: CorsRegistry) {
    registry.addMapping("/v1")
            .allowedOrigins(appProperties.allowedOrigins)
            .allowedMethods(appProperties.allowedMethods)
            .allowedMethods(
                    HttpMethod.GET.name,
                    HttpMethod.POST.name,
                    HttpMethod.PUT.name,
                    HttpMethod.DELETE.name
            )
            .allowCredentials(true)
            .maxAge(7200);
  }

}
