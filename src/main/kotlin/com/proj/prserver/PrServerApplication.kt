package com.proj.prserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing



@ConfigurationPropertiesScan
@SpringBootApplication
class PrServerApplication

fun main(args: Array<String>) {
    runApplication<PrServerApplication>(*args)
}
