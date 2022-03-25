package com.proj.prserver.service.impl

import com.fasterxml.jackson.databind.ObjectMapper
import com.proj.prserver.enums.Event
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
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType

@Service
@Transactional
class WebhookServiceImpl(
        val restTemplate: RestTemplate,
        val webHookRepository: WebHookRepository
) : WebHookService {

  private val log = KotlinLogging.logger {}

  fun getPullRequest(): String? {
    log.info { "GET person" }

    val headers = HttpHeaders()
    headers.set("Accept", "application/vnd.github.v3+json")
    val entity = HttpEntity<Map<String, Any>>(headers)

    //api를 호출하여 데이터를 MAP타입으로 전달 받는다.
    val resultMap : ResponseEntity<String>
            = restTemplate.exchange("https://api.github.com/repos/9iseong/pr-test/pulls?state=all", HttpMethod.GET, entity, typeRef<String>())


    log.info { "resultMap ${resultMap.getBody()}" }

    //데이터를 string형태로 파싱해줌
    val mapper = ObjectMapper()
    var jsonInString = mapper.writeValueAsString(resultMap.getBody());

    log.info { "jsonInString ${jsonInString}" }

    return resultMap.getBody()
  }

  override fun save(
    event: String,
    hookId: String,
    delivery: String,
    strPayload: String,
    mapPayload: Map<String, Object>
  ) {

    when(event){
      Event.fork.type -> {
        // fork 이벤트는 a Repository 를 포크한 사용자 1 의 정보가 데이터로 분류되어야함.
        //user fork를 실행한 사용자 의 관련 훅을 pr 기준으로 시간순으로 조회할수 있어야함.
        //최초 fork 한 사용자의 식별값 만으로, 상기 명시된 항목이 시간순으로 모두 나열되어야함.
      }
      Event.pull_request.type -> {
        //pull_request 는 open 을 행한 사람이 소유자. 소유자의 정보가 식별가능해야함.
        //데이터는 repository > pr , user > pr 형태로 관리되어야함.
        //repository에서 일어난 pr 관련 훅을 시간순으로 조회가능할수 있어야함.
      }
      Event.pull_request_review.type -> {
        //pull_request reviewer 가 지정된 경우 지정된 리뷰어의 식별정보, assignee 존재할 경우 해당 사용자의 식별정보를 저장해야함.
        //pull_request_review, pull_request_review_comment 역시 실행한 사람, 작성한 사람, action 등의 어떤행위를 했는지등 행위에 따른 분류가 가능해야함.
      }
      Event.pull_request_review_comment.type -> {
        //pull_request_review, pull_request_review_comment 역시 실행한 사람, 작성한 사람, action 등의 어떤행위를 했는지등 행위에 따른 분류가 가능해야함.
      }
    }

    return webHookRepository.save(event, hookId, delivery, strPayload, mapPayload)

  }

  override fun getApi(url: String) {
    TODO("Not yet implemented")
  }

  override fun getByHook(id: String) {
    TODO("Not yet implemented")
  }


}
