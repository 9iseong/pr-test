package com.proj.prserver.controller

import com.proj.prserver.service.impl.PullRequestService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/v1/pull-request")
class PullRequestController(
  val pullRequestService: PullRequestService
) {

    @GetMapping
    fun test():String? {
      return pullRequestService.getPullRequest()
    }

}
