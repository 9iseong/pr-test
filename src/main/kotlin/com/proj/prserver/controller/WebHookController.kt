package com.proj.prserver.controller

import com.proj.prserver.entity.WebHook
import com.proj.prserver.repository.WebHookRepository
import com.proj.prserver.service.PullRequestService
import lombok.extern.slf4j.Slf4j
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

private val log = KotlinLogging.logger {}

@RestController
@RequestMapping("/v1/webhook")
class WebHookController(
        var webHookRepository: WebHookRepository
) {

    @PostMapping("/payload")
    fun test(@RequestBody event: String):ResponseEntity<WebHook>? {

      log.info { "webhook ${event}" }

      val save = webHookRepository.save(WebHook(event))
      return ResponseEntity.ok(save)
    }

}
