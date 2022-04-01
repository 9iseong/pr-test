package com.proj.prserver.entity

import com.fasterxml.jackson.annotation.JsonFormat
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.*



@Entity
@Table(name = "pr_hook")
data class WebHook(
  val event: String? = null,
  val hookId: String? = null,
  val delivery: String? = null,
  @Column(length = 65000)
  val payload: String? = null,

  @ManyToOne
  @JoinColumn(name = "userId")
  val user: User,

  @ManyToOne
  @JoinColumn(name = "repoId")
  val repository: Repo,

  @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone="Asia/Seoul")
  var created_at : LocalDateTime = LocalDateTime.now()
) {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  var id: Long? = null;

}
