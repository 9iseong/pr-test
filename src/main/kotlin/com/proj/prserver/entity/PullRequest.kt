package com.proj.prserver.entity

import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity
data class PullRequest(
  val state: String,
  val number: Long,
  val url: String,
  val id: String,
  val locked: Boolean,
  val title: String,
  @ManyToOne
  val user: User,
  val body: String,
  val createdAt: LocalDateTime = LocalDateTime.now(),
  val updatedAt: LocalDateTime,
  val closedAt: LocalDateTime? = null,
  val mergedAd: LocalDateTime? = null,
)
{
    @Id @GeneratedValue
    var no: Long? = null
}
