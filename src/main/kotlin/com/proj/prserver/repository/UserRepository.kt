package com.proj.prserver.repository

import com.proj.prserver.entity.User
import com.proj.prserver.entity.WebHook
import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<User, Long>
{
}
