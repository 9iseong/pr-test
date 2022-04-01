package com.proj.prserver.service.impl

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.proj.prserver.common.GithubApi
import com.proj.prserver.common.readValue
import com.proj.prserver.common.toDataClass
import com.proj.prserver.entity.PullRequest
import com.proj.prserver.entity.Repo
import com.proj.prserver.entity.User
import com.proj.prserver.entity.WebHook
import com.proj.prserver.enums.Event
import com.proj.prserver.repository.PullRequestRepository
import com.proj.prserver.repository.RepoRepository
import com.proj.prserver.repository.UserRepository
import com.proj.prserver.repository.WebHookRepository
import com.proj.prserver.service.RepoService
import com.proj.prserver.service.UserService
import com.proj.prserver.service.WebHookService
import mu.KotlinLogging
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.client.RestTemplate
import java.time.LocalDateTime

@Service
@Transactional(readOnly = true)
class WebhookServiceImpl(
  private val restTemplate: RestTemplate,
  private val webHookRepository: WebHookRepository,
  private val pullRequestRepository: PullRequestRepository,
  private val userRepository: UserRepository,
  private val repoRepository: RepoRepository,
  private val githubApi: GithubApi,
  private val userService: UserService,
  private val repoService: RepoService

) : WebHookService {

  private val log = KotlinLogging.logger {}

  @Transactional
  override fun save(
    event: String,
    hookId: String,
    delivery: String,
    strPayload: String,
    mapPayload: Map<String, Object>
  ) : WebHook {

    val mapper = ObjectMapper()
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    val objUser: Map<String, Any> =  mapPayload.get("sender") as Map<String, Any>
    val findUser = userService.findByIdOrSave(objUser.get("id").toString().toLong(), objUser.toDataClass())

    val objRepository: Map<String, Any> =  mapPayload.get("repository") as Map<String, Any>
    val repo:Repo = repoService.findByIdOrSave(objRepository.get("id").toString().toLong(), objRepository.toDataClass())


    var createdAt = LocalDateTime.now();

    when(event){
      Event.FORK.type -> {
        val objForkee: Map<String, Any> =  mapPayload.get("forkee") as Map<String, Any>
        createdAt = LocalDateTime.parse(objForkee.get("created_at").toString())
      }
      Event.PULL_REQUEST.type -> {
        //pull_request 는 open 을 행한 사람이 소유자. 소유자의 정보가 식별가능해야함.

        val strAction: String? =  mapPayload.get("action").toString()

        if(strAction.equals("opened")){

        }

        //데이터는 repository > pr , user > pr 형태로 관리되어야함.
        //repository에서 일어난 pr 관련 훅을 시간순으로 조회가능할수 있어야함.

        val mapPayload:Map<String, Any> = mapper.readValue(strPayload);
        val objPullRequest: Map<String, Any> = mapPayload.get("pull_request") as Map<String, Any>
        createdAt = LocalDateTime.parse(objPullRequest.get("created_at").toString())

        pullRequestRepository.save(objPullRequest.toDataClass() as PullRequest);

      }
      Event.PULL_REQUEST_REVIEW.type -> {
        //pull_request reviewer 가 지정된 경우 지정된 리뷰어의 식별정보, assignee 존재할 경우 해당 사용자의 식별정보를 저장해야함.
        //pull_request_review, pull_request_review_comment 역시 실행한 사람, 작성한 사람, action 등의 어떤행위를 했는지등 행위에 따른 분류가 가능해야함.

        val objPullRequest: Map<String, Any> = mapPayload.get("review") as Map<String, Any>
        createdAt = LocalDateTime.parse(objPullRequest.get("submitted_at").toString())

      }
      Event.PULL_REQUEST_REVIEW_COMMENT.type -> {
        //pull_request_review, pull_request_review_comment 역시 실행한 사람, 작성한 사람, action 등의 어떤행위를 했는지등 행위에 따른 분류가 가능해야함.
        val objPullRequest: Map<String, Any> = mapPayload.get("comment") as Map<String, Any>
        createdAt = LocalDateTime.parse(objPullRequest.get("created_at").toString())


      }
    }


    return webHookRepository.save(WebHook(event, hookId, delivery, strPayload, findUser, repo, createdAt))
  }


  override fun getByHook(id: String) {
    TODO("Not yet implemented")
  }


}
