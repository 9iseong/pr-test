package com.proj.prserver.service.impl

import com.proj.prserver.common.toDataClass
import com.proj.prserver.entity.User
import com.proj.prserver.repository.UserRepository
import com.proj.prserver.service.UserService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserServiceImpl(
  private val userRepository: UserRepository
) : UserService {

  @Transactional
  override fun findByIdOrSave(no: Long, saveUser: User): User {
    val opUser = userRepository.findById(no)
    return if(opUser.isPresent) opUser.get() else userRepository.save(saveUser)
  }
}
