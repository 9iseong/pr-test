package com.proj.prserver.repository

import com.proj.prserver.entity.PullRequest
import org.springframework.data.repository.CrudRepository

interface PullRequestRepository : CrudRepository<PullRequest, Long>
{
}
