package com.proj.prserver.entity

import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class User(
  @Id val id: String,
  val login: String = "",
  val avatarUrl: String = "",
  val gravatarId: String = "",
  val url: String = "",
  val htmlUrl: String = "",
  val followersUrl: String = "",
  val followingUrl: String = "",
  val gistsUrl: String = "",
  val starredUrl: String = "",
  val subscriptionsUrl: String = "",
  val organizationUrl: String = "",
  val reposUrl: String = "",
  val eventsUrl: String = "",
  val receivedEventUrl: String = "",
  val type: String = "",
  val siteAdmin: String = ""
) {
}
