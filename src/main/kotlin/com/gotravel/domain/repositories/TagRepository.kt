package com.gotravel.domain.repositories

import com.gotravel.domain.entities.Tag
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface TagRepository : MongoRepository<Tag, String> {
    fun findByName(name: String): Tag?
}