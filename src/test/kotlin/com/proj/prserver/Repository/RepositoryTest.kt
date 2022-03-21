package com.proj.prserver.Repository

import com.proj.prserver.repository.PullRequestRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager

@DataJpaTest
class RepositoryTest @Autowired constructor(
        val entityManager: TestEntityManager,
        val pullRequestRepository: PullRequestRepository
){

}