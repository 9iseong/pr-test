package com.proj.prserver.entity

import com.fasterxml.jackson.annotation.JsonFormat
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.domain.Persistable
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "pr_user")
data class User(
  @Id val id: Long,
  val login: String = "",
  val avatar_url: String = "",
  val gravatar_id: String = "",
  val url: String = "",
  val html_url: String = "",
  val followers_url: String = "",
  val following_url: String = "",
  val gists_url: String = "",
  val starred_url: String = "",
  val subscriptions_url: String = "",
  val organizations_url: String = "",
  val repos_url: String = "",
  val events_url: String = "",
  val received_events_url: String = "",
  val type: String = ""
) : Persistable<Long> {

  @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone="Asia/Seoul")
  var created_at : LocalDateTime = LocalDateTime.now();

  override fun getId(): Long? {
    return id
  }

  override fun isNew(): Boolean {
    return created_at == null;
  }
}
