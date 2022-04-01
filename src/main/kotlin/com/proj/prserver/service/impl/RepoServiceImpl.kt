package com.proj.prserver.service.impl

import com.proj.prserver.common.toDataClass
import com.proj.prserver.entity.Repo
import com.proj.prserver.repository.RepoRepository
import com.proj.prserver.service.RepoService
import com.proj.prserver.service.UserService
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class RepoServiceImpl(
  private val restTemplate: RestTemplate,
  private val repoRepository: RepoRepository,
  private val userService: UserService
) : RepoService {


  override fun get(): List<Repo> {
    TODO("Not yet implemented")
  }

  override fun getById(id: String): List<Repo> {
    TODO("Not yet implemented")
  }

  override fun getByUser(userId: String) {
    TODO("Not yet implemented")
  }

  override fun findByIdOrSave(no: Long, saveRepo: Repo): Repo {
    val repoOptional = repoRepository.findById(no)

    val funSave: () -> Repo = {
      val user = userService.findByIdOrSave(saveRepo.owner.id, saveRepo.owner)
      saveRepo.owner = user
      repoRepository.save(saveRepo)
    }

    return if(repoOptional.isPresent) repoOptional.get() else funSave()
  }


}
