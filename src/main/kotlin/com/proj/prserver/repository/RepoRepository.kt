package com.proj.prserver.repository

import com.proj.prserver.entity.Repo
import org.springframework.data.repository.CrudRepository

interface RepoRepository : CrudRepository<Repo, Long>
{
}
