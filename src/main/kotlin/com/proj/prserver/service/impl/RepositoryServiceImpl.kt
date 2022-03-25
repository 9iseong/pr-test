package com.proj.prserver.service.impl

import com.fasterxml.jackson.databind.ObjectMapper
import com.proj.prserver.entity.Repository
import com.proj.prserver.service.PullRequestService
import com.proj.prserver.service.RepositoryService
import mu.KotlinLogging
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType

@Service
class RepositoryServiceImpl(
        val restTemplate: RestTemplate
) : RepositoryService {


  override fun get(): List<Repository> {
    TODO("Not yet implemented")
  }

  override fun getById(id: String): List<Repository> {
    TODO("Not yet implemented")
  }

  override fun getByUser(userId: String) {
    TODO("Not yet implemented")
  }


}
