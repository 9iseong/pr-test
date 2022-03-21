package com.proj.prserver.entity

import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class PullRequest {

    @Id
    @GeneratedValue
    var id: Long? = null;
    var createdAt : LocalDateTime = LocalDateTime.now();

}
