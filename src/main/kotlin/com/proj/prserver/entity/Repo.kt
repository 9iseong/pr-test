package com.proj.prserver.entity

import com.fasterxml.jackson.annotation.JsonFormat
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.domain.Persistable
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.*


@Entity
@Table(name = "pr_repo")
data class Repo(
  @Id val id: Long,
  val node_id: String,
  val name: String,
  val full_name: String,
  val private:Boolean,

  @JoinColumn(name = "ownerId")
  @ManyToOne
  var owner:User,

  val html_url: String,
  val description:String? = null,
  val fork: Boolean,
  val url:String,
  val forks_url: String,
  val keys_url:String,
  val collaborators_url: String,
  val teams_url: String,
  val hooks_url: String,
  val issue_events_url: String,
  val events_url: String,
  val assignees_url: String,
  val branches_url: String,
  val tags_url: String,
  val blobs_url: String,
  val git_tags_url: String,
  val git_refs_url: String,
  val trees_url: String,
  val statuses_url: String,
  val languages_url: String,
  val stargazers_url: String,
  val contributors_url: String,
  val subscribers_url: String,
  val subscription_url: String,
  val commits_url: String,
  val git_commits_url: String,
  val comments_url: String,
  val issue_comment_url: String,
  val contents_url: String,
  val compare_url: String,
  val merges_url: String,
  val archive_url: String,
  val downloads_url: String,
  val issues_url: String,
  val pulls_url: String,
  val milestones_url: String,
  val notifications_url: String,
  val labels_url: String,
  val releases_url: String,
  val deployments_url: String,
  val created_at: LocalDateTime,
  val updated_at: LocalDateTime,
  val pushed_at: LocalDateTime,
  val git_url: String,
  val ssh_url: String,
  val clone_url: String,
  val svn_url: String,
  val homepage: String,
  val size: Integer,
  val stargazers_count: Integer,
  val watchers_count: Integer,
  val language: String?,
  val has_issues: Boolean,
  val has_projects: Boolean,
  val has_downloads: Boolean,
  val has_wiki: Boolean,
  val has_page: Boolean,
  val forks_count: Integer,
  val mirror_url: String?,
  val archived: Boolean,
  val disabled: Boolean,
  val open_issues_count: Integer,
  val forks: Integer,
  val open_issues: Integer,
  val watchers: Integer,
  val default_branch: String

) : Persistable<Long> {

  @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone="Asia/Seoul")
  var repo_created_at : LocalDateTime = LocalDateTime.now();

  override fun getId(): Long? {
    return id
  }

  override fun isNew(): Boolean {
    return repo_created_at == null;
  }


}
