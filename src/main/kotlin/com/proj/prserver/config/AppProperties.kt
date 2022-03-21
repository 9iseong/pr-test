package com.proj.prserver.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import org.springframework.stereotype.Component
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.*


@ConstructorBinding
@ConfigurationProperties("application.pr")
data class AppProperties  (
    var allowedOrigins: List<String>,
    var allowedMethods: List<String>,
    var formatDate: DateTimeFormatter,
    var formatDateTime: DateTimeFormatter
) {

}

