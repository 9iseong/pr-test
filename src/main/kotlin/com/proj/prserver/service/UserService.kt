package com.proj.prserver.service

import com.proj.prserver.entity.User

interface UserService {

  /**
   * 검색 후 없을 시 저장
   */
  fun findByIdOrSave(no:Long, user:User): User
}
