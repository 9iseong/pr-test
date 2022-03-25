package com.proj.prserver.service

import com.proj.prserver.entity.Repository

interface PullRequestService {

  /**
   * 사용자 별 PR
   */
  fun getByUser(userId:String)

  /**
   * PR 상세
   */
  fun getById(id:String)

}
