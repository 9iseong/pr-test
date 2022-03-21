package com.proj.prserver.config

import com.fasterxml.jackson.databind.MapperFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import java.time.LocalDate
import java.time.LocalDateTime

@Configuration
class WebConfig: WebMvcConfigurer {
//    val appProperties:AppProperties

  override fun addCorsMappings(registry: CorsRegistry) {
    registry.addMapping("/**")
//            .allowedOrigins("http://localhost:3000")
            .allowedMethods(
                    HttpMethod.GET.name,
                    HttpMethod.POST.name,
                    HttpMethod.PUT.name,
                    HttpMethod.DELETE.name
            )
  }

  override fun configureMessageConverters(converters: MutableList<HttpMessageConverter<*>>) {
    converters.add(MappingJackson2HttpMessageConverter(jackson2ObjectMapperBuilder().build<ObjectMapper>()))
  }

  @Bean
  fun jackson2ObjectMapperBuilder(): Jackson2ObjectMapperBuilder? {
    return Jackson2ObjectMapperBuilder()
            .failOnUnknownProperties(false) // SpringBoot default
            .featuresToDisable(MapperFeature.DEFAULT_VIEW_INCLUSION) // SpringBoot default
            .featuresToEnable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS) // SpringBoot default
//            .serializerByType(LocalDateTime::class.java, LocalDateTimeSerializer(appProperties.getFormatDateTime()))
//            .serializerByType(LocalDate::class.java, LocalDateSerializer(appProperties.getFormatDate()))
  }

}
