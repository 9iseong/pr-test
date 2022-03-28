package com.proj.prserver.service.impl

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.proj.prserver.common.GithubApi
import com.proj.prserver.controller.readValue
import com.proj.prserver.entity.PullRequest
import com.proj.prserver.entity.Repository
import com.proj.prserver.entity.User
import com.proj.prserver.entity.WebHook
import com.proj.prserver.enums.Event
import com.proj.prserver.repository.RepoRepository
import com.proj.prserver.repository.UserRepository
import com.proj.prserver.repository.WebHookRepository
import com.proj.prserver.service.WebHookService
import mu.KotlinLogging
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.client.RestTemplate
import java.util.*

@Service
@Transactional
class WebhookServiceImpl(
  val restTemplate: RestTemplate,
  val webHookRepository: WebHookRepository,
  val userRepository: UserRepository,
  val repoRepository: RepoRepository,
  val githubApi: GithubApi
) : WebHookService {

  private val log = KotlinLogging.logger {}

  override fun save(
    event: String,
    hookId: String,
    delivery: String,
    strPayload: String,
    mapPayload: Map<String, Object>
  ) : WebHook {

    when(event){
      Event.FORK.type -> {
        //fork 이벤트는 a Repository 를 포크한 사용자 1 의 정보가 데이터로 분류되어야함.
        //user fork를 실행한 사용자 의 관련 훅을 pr 기준으로 시간순으로 조회할수 있어야함.
        //최초 fork 한 사용자의 식별값 만으로, 상기 명시된 항목이 시간순으로 모두 나열되어야함.

//        webHookRepository.save(WebHook(event, hookId, delivery, strPayload, objUser))
      }
      Event.PULL_REQUEST.type -> {
        //pull_request 는 open 을 행한 사람이 소유자. 소유자의 정보가 식별가능해야함.

        val strAction: String? =  mapPayload.get("action").toString()

        if(strAction.equals("opened")){

        }

        //데이터는 repository > pr , user > pr 형태로 관리되어야함.
        //repository에서 일어난 pr 관련 훅을 시간순으로 조회가능할수 있어야함.


        val strPullRequest: String? =  mapPayload.get("pull_request").toString()
        val objPullRequest:PullRequest = ObjectMapper().readValue(strPullRequest, object: TypeReference<PullRequest>() {});

        log.debug { "repository  $objPullRequest" }

      }
      Event.PULL_REQUEST_REVIEW.type -> {
        //pull_request reviewer 가 지정된 경우 지정된 리뷰어의 식별정보, assignee 존재할 경우 해당 사용자의 식별정보를 저장해야함.
        //pull_request_review, pull_request_review_comment 역시 실행한 사람, 작성한 사람, action 등의 어떤행위를 했는지등 행위에 따른 분류가 가능해야함.
      }
      Event.PULL_REQUEST_REVIEW_COMMENT.type -> {
        //pull_request_review, pull_request_review_comment 역시 실행한 사람, 작성한 사람, action 등의 어떤행위를 했는지등 행위에 따른 분류가 가능해야함.
      }
    }


    val strUser: String? =  mapPayload.get("sender").toString()
    val objUser:User = ObjectMapper().readValue(strUser, object: TypeReference<User>() {});

    log.debug { "user $objUser" }
//
//        val findUser: Optional<User> = userRepository.findById(objUser.id)
//
//        if(!findUser.isPresent){
//          userRepository.save(objUser);
//        }


    val strRepository: String? =  mapPayload.get("repository").toString()
    val objRepository:Repository = ObjectMapper().readValue(strRepository, object: TypeReference<Repository>() {});

    log.debug { "repository  $objRepository" }
//
//        val findRepository: Optional<Repository> = repoRepository.findById(objRepository.id)
//
//        if(!findRepository.isPresent){
//          repoRepository.save(objRepository);
//        }

    return WebHook(event, hookId, delivery, strPayload, objUser, objRepository)

  }


  override fun getByHook(id: String) {
    TODO("Not yet implemented")
  }


}
