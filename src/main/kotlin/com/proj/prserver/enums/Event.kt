package com.proj.prserver.enums

enum class Event(
  val type: String,
  val apiUrl:String,
) {

  FORK("fork", "/repos/{userId}/{repo}/forks"),
  PULL_REQUEST("pull_request", "/repos/{userId}/{repo}/pulls"),
  PULL_REQUEST_REVIEW("pull_request_review", "/repos/{userId}/{repo}/pulls/{pull_number}/reviews"),
  PULL_REQUEST_REVIEW_COMMENT("pull_request_review_comment", "/repos/{userId}/{repo}/pulls/{pull_number}/reviews/{review_id}/comments")

}
