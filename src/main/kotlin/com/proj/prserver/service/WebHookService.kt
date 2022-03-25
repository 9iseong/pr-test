package com.proj.prserver.service

import com.proj.prserver.entity.Repository
import com.proj.prserver.entity.WebHook

interface WebHookService {

  /**
   * hook 로그 저장
   */
  fun save(event:String, hookId:String, delivery:String, strPayload:String, mapPayload:Map<String,Object>): WebHook

  /**
   * hook event 조회
   */
  fun getApi(url: String)

  /**
   * hook 로그 조회
   */
  fun getByHook(id:String)

}
