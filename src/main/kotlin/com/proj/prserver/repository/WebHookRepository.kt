package com.proj.prserver.repository

import com.proj.prserver.entity.WebHook
import org.springframework.data.repository.CrudRepository

interface WebHookRepository : CrudRepository<WebHook, Long>
{
}
