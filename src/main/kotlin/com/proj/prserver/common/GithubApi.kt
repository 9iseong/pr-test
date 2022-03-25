package com.proj.prserver.common

import com.fasterxml.jackson.databind.ObjectMapper
import mu.KotlinLogging
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate


inline fun <reified T : Any> typeRef(): ParameterizedTypeReference<T> = object : ParameterizedTypeReference<T>() {}

@Component
class GithubApi (
  val restTemplate: RestTemplate
){

  private val log = KotlinLogging.logger {}

  fun get(
    userId:String,
    repo: String,
    event: String,
    param: String
  ):String {

    val url = "https://api.github.com/repos/${userId}/${repo}/${event}?${param}"

    val headers = HttpHeaders()
    headers.set("Accept", "application/vnd.github.v3+json")

    //api를 호출하여 데이터를 MAP타입으로 전달 받는다.
    val resultMap : ResponseEntity<String>
            = restTemplate.exchange(url, HttpMethod.GET, HttpEntity<Map<String, Any>>(headers), typeRef<String>())

    //데이터를 string형태로 파싱해줌
    val mapper = ObjectMapper()
    var jsonInString = mapper.writeValueAsString(resultMap.getBody());

    log.info { "jsonInString ${jsonInString}" }

    return jsonInString
  }

}
