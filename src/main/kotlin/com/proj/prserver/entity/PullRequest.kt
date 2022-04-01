package com.proj.prserver.entity

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "pr_pull_request")
data class PullRequest(
  val url: String,
  @Id val id: String,
  val node_id: String,
  val html_url: String,
  val diff_url: String,
  val patch_url: String,
  val issue_url: String,
  val number: Long,
  val state: String,
  val locked: Boolean,
  val title: String,

  @JoinColumn(name = "ownerId")
  @ManyToOne
  var owner: User,

  val body: String,
  val created_at: LocalDateTime = LocalDateTime.now(),
  val updated_at: LocalDateTime,
  val closed_at: LocalDateTime? = null,
  val merged_ad: LocalDateTime? = null,

//  val assignee: String? = null,
//  val assignees: String? = null,
//  val requestedReviewers: String? = null,
//  val requestedteams: String
//  val labels: Array<String>,
//  val milestone

  val commits_url: String,
  val review_comments_url: String,
  val review_comment_url: String,
  val comments_url: String,
  val statuses_url: String
)
{
}
