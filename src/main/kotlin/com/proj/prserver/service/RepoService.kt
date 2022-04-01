package com.proj.prserver.service

import com.proj.prserver.entity.Repo

interface RepoService {

  /**
   * 리포지토리 목록
   */
  fun get():List<Repo>

  /**
   * 리포지토리 상세
   */
  fun getById(id:String):List<Repo>

  /**
   * 리포지토리 별 사용자 목록
   */
  fun getByUser(userId:String)

  /**
   * 검색 후 없을 시 저장
   */
  fun findByIdOrSave(no: Long, saveRepo: Repo): Repo

}
