package com.proj.prserver.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.util.ISO8601DateFormat
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate
import javax.annotation.PostConstruct


@Configuration
class ServerConfig {


  @Autowired
  lateinit var objectMapper: ObjectMapper

  @PostConstruct
  fun init() {
    objectMapper.dateFormat = ISO8601DateFormat()
  }

  @Bean
  fun restTemplate(): RestTemplate {
    return RestTemplateBuilder().build()
  }

}
