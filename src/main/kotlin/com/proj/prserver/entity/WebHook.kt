package com.proj.prserver.entity

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "pr_hook")
data class WebHook(
  val event: String? = null,
  val hookId: String? = null,
  val delivery: String? = null,
  val payload: String? = null,

  @JoinColumn(name = "userId")
  @ManyToOne(fetch=FetchType.LAZY)
  val user: User,

  @JoinColumn(name = "repoId")
  @ManyToOne(fetch=FetchType.LAZY)
  val repository: Repository

) {

  @Id
  @GeneratedValue
  var id: String? = null;

  @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone="Asia/Seoul")
  var createdAt : LocalDateTime = LocalDateTime.now();

}
