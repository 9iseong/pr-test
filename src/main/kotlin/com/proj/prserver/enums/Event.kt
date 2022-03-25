package com.proj.prserver.enums

enum class Event(
  val type: String,
  val apiUrl:String,
) {

  fork("fork", "/repos/{owner}/{repo}/forks"),
  pull_request("pull_request", "/repos/{owner}/{repo}/pulls"),
  pull_request_review("pull_request_review", "/repos/{owner}/{repo}/pulls/{pull_number}/reviews"),
  pull_request_review_comment("pull_request_review_comment", "/repos/{owner}/{repo}/pulls/{pull_number}/reviews/{review_id}/comments")

}
