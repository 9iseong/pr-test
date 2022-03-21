package com.proj.prserver.controller

import com.proj.prserver.service.PullRequestService
import lombok.extern.slf4j.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController


@Slf4j
@RestController
class APITestController(
  val pullRequestService: PullRequestService
) {

    @GetMapping("/")
    fun test():String? {
      return pullRequestService.getPullRequest()
    }

}
