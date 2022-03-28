package com.proj.prserver.common

import com.fasterxml.jackson.databind.ObjectMapper
import com.proj.prserver.enums.Event
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

  fun getFork(
    userId:String,
    repo: String,
    param: String
  ):String {
    return get(Event.FORK, userId, repo, "", "", param);
  }

  fun getPullRequest(
    userId: String,
    repo: String,
    param: String
  ):String {
    return get(Event.PULL_REQUEST, userId, repo, "", "", param);
  }

  fun getPullRequestReview(
    event: String,
    userId:String,
    repo: String,
    pull_number: String,
    param: String
  ):String {
    return get(Event.PULL_REQUEST_REVIEW, userId, repo, pull_number, "", param);
  }

  fun getPullRequestReviewComment(
   event: String,
   userId:String,
   repo: String,
   pull_number: String,
   review_id: String,
   param: String
  ):String {
    return get(Event.PULL_REQUEST_REVIEW_COMMENT, userId, repo, pull_number, review_id, param);
  }

  private fun get(
    event: Event,
    userId:String,
    repo: String,
    pull_number: String,
    review_id: String,
    param: String
  ):String {

    var url:String = "https://api.github.com";

    when(event){
      Event.FORK -> {
        url += Event.FORK.apiUrl;
        url.replace("{userId}", userId)
        url.replace("{repo}", repo)
      }
      Event.PULL_REQUEST -> {
        url += Event.FORK.apiUrl;
        url.replace("{userId}", userId)
        url.replace("{repo}", repo)
      }
      Event.PULL_REQUEST_REVIEW -> {
        url += Event.FORK.apiUrl;
        url.replace("{userId}", userId)
        url.replace("{repo}", repo)
        url.replace("{pull_number}", pull_number)
      }
      Event.PULL_REQUEST_REVIEW_COMMENT -> {
        url += Event.FORK.apiUrl;
        url.replace("{userId}", userId)
        url.replace("{repo}", repo)
        url.replace("{pull_number}", pull_number)
        url.replace("{review_id}", review_id)
      }
    }

    url += "?$param"

    val headers = HttpHeaders()
    headers.set("Accept", "application/vnd.github.v3+json")

    //api를 호출하여 데이터를 MAP타입으로 전달 받는다.
    val resultMap: ResponseEntity<String>
            = restTemplate.exchange(url, HttpMethod.GET, HttpEntity<Map<String, Any>>(headers), typeRef<String>())

    //데이터를 string형태로 파싱해줌
    val mapper = ObjectMapper()
    var jsonInString = mapper.writeValueAsString(resultMap.getBody());

    log.debug { "$jsonInString" }

    return jsonInString
  }

}
