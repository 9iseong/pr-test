package com.proj.prserver.controller

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.proj.prserver.common.getStrURLDecode
import com.proj.prserver.entity.WebHook
import com.proj.prserver.repository.WebHookRepository
import com.proj.prserver.service.impl.ForkRequestServiceImpl
import com.proj.prserver.service.impl.PullRequestReviewCommentService
import com.proj.prserver.service.impl.PullRequestReviewService
import com.proj.prserver.service.impl.PullRequestService
import mu.KotlinLogging
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

inline fun <reified T> ObjectMapper.readValue(s: String): T = this.readValue(s, object : TypeReference<T>() {})


@RestController
@RequestMapping("/v1/webhook")
class WebHookController(
  val webHookRepository: WebHookRepository,
  val forkRequestService: ForkRequestServiceImpl,
  val pullRequestService: PullRequestService,
  val pullRequestReviewService: PullRequestReviewService,
  val pullRequestReviewCommentService: PullRequestReviewCommentService
) {
    private val log = KotlinLogging.logger {}

    @PostMapping(path = ["/payload"], consumes = [MediaType.APPLICATION_FORM_URLENCODED_VALUE])
    fun test(
      @RequestHeader(value="X-GitHub-Event") event:String,
      @RequestHeader(value="X-GitHub-Hook-ID") hookId:String,
      @RequestHeader(value="X-GitHub-Delivery") delivery:String,
      body: Body
    ):ResponseEntity<WebHook>? {

      val payload:String = getStrURLDecode(body.payload);

      val mapper = ObjectMapper()
      val map:Map<String,Object> = mapper.readValue(payload);

      log.info { "header ${event} ${hookId} ${delivery}" }
      log.info { "webhook ${map}" }

      when(event){
        "fork" -> {  }
        "pull_request" -> {}
        "pull_request_review" -> {}
        "pull_request_review_comment" -> {}
      }

      val save = webHookRepository.save(WebHook(event, hookId, delivery, body.payload))
      return ResponseEntity.ok(save)
    }

  data class Body(
    val payload: String
  )


}
