package com.proj.prserver.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeFormatter.ofPattern


@ConstructorBinding
@ConfigurationProperties("app.pr")
data class AppProperties  (
    var allowedOrigins: String,
    var allowedMethods: String,
    var formatDate: String,
    var formatDateTime: String
) {

  fun getFormatDate(): DateTimeFormatter = ofPattern(formatDate)
  fun getFormatDateTime(): DateTimeFormatter = ofPattern(formatDateTime)

}

