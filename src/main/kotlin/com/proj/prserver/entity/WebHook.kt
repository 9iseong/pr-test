package com.proj.prserver.entity

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity
data class WebHook(
  val event: String? = null,
  val hookId: String? = null,
  val delivery: String? = null,
  val payload: String? = null,
  @ManyToOne
  val user: User
) {

  @Id
  @GeneratedValue
  var id: Long? = null;

  @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone="Asia/Seoul")
  var createdAt : LocalDateTime = LocalDateTime.now();

}
