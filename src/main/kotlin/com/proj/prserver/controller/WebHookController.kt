package com.proj.prserver.controller

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.proj.prserver.common.getStrURLDecode
import com.proj.prserver.common.readValue
import com.proj.prserver.entity.WebHook
import com.proj.prserver.repository.WebHookRepository
import com.proj.prserver.service.WebHookService
import mu.KotlinLogging
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*




@RestController
@RequestMapping("/v1/webhook")
class WebHookController(
  val webHookRepository: WebHookRepository,
  val webHookService: WebHookService,
) {
    private val log = KotlinLogging.logger {}

    @PostMapping(path = ["/payload"], consumes = [MediaType.APPLICATION_FORM_URLENCODED_VALUE])
    fun test(
      @RequestHeader(value="X-GitHub-Event") event:String,
      @RequestHeader(value="X-GitHub-Hook-ID") hookId:String,
      @RequestHeader(value="X-GitHub-Delivery") delivery:String,
      body: Body
    ):ResponseEntity<WebHook>? {

      val strPayload:String = getStrURLDecode(body.payload);

      val mapper = ObjectMapper()
      val mapPayload:Map<String, Object> = mapper.readValue(strPayload);

      log.debug { "$strPayload" }

      val saveHook = webHookService.save(event, hookId, delivery, strPayload, mapPayload)

      return ResponseEntity.ok(saveHook)
    }

  data class Body(
    val payload: String
  )


}
