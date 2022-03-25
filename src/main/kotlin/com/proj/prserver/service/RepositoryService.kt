package com.proj.prserver.service

import com.proj.prserver.entity.Repository

interface RepositoryService {

  /**
   * 리포지토리 목록
   */
  fun get():List<Repository>

  /**
   * 리포지토리 상세
   */
  fun getById(id:String):List<Repository>

  /**
   * 리포지토리 별 사용자 목록
   */
  fun getByUser(userId:String)

}
