package com.proj.prserver.entity

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "pr_pull_request")
data class PullRequest(
  val url: String,
  @Id val id: String,
  val nodeId: String,
  val htmlUrl: String,
  val diffUrl: String,
  val patchUrl: String,
  val issueUrl: String,
  val number: Long,
  val state: String,
  val locked: Boolean,
  val title: String,

  @JoinColumn(name = "ownerId")
  @ManyToOne(fetch=FetchType.LAZY)
  val owner: User? = null,

  @JoinColumn(name = "userId")
  @ManyToOne(fetch=FetchType.LAZY)
  val user: User,
  val body: String,
  val createdAt: LocalDateTime = LocalDateTime.now(),
  val updatedAt: LocalDateTime,
  val closedAt: LocalDateTime? = null,
  val mergedAd: LocalDateTime? = null,
//  val assignee: String? = null,
//  val assignees: String? = null,
//  val requestedReviewers: String? = null,
//  val requestedteams: String
//  val labels: Array<String>,
//  val milestone
  val commitsUrl: String,
  val reviewCommentsUrl: String,
  val reviewCommentUrl: String,
  val commentsUrl: String,
  val statusesUrl: String
)
{
}
