package com.proj.prserver.service.impl

import com.fasterxml.jackson.databind.ObjectMapper
import mu.KotlinLogging
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class PullRequestService(
        val restTemplate: RestTemplate
) {


  private val log = KotlinLogging.logger {}

  fun getPullRequest(): String? {
    log.info { "GET person" }

    val headers = HttpHeaders()
    headers.set("Accept", "application/vnd.github.v3+json")
    val entity = HttpEntity<Map<String, Any>>(headers)

    //api를 호출하여 데이터를 MAP타입으로 전달 받는다.
    val resultMap : ResponseEntity<String>
            = restTemplate.exchange("https://api.github.com/repos/9iseong/pr-test/pulls?state=all", HttpMethod.GET, entity, typeRef<String>())


    log.info { "resultMap ${resultMap.getBody()}" }

    //데이터를 string형태로 파싱해줌
    val mapper = ObjectMapper()
    var jsonInString = mapper.writeValueAsString(resultMap.getBody());

    log.info { "jsonInString ${jsonInString}" }

    return resultMap.getBody()
  }



}
