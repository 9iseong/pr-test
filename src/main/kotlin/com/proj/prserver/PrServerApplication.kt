package com.proj.prserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PrServerApplication

fun main(args: Array<String>) {
    runApplication<PrServerApplication>(*args)
}
