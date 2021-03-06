package com.gotravel.domain.entities

import com.gotravel.application.dto.Author
import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

@Document
data class Article(
        var slug: String? = "",
        var title: String? = "",
        var description: String? = "",
        var body: String? = "",
        //Lista de tags
        val tagList: MutableList<Tag> = mutableListOf(),
        var createdAt: String = formatter.format(LocalDateTime.now()),
        var updatedAt: String = formatter.format(LocalDateTime.now()),
        var comments: MutableList<Comment> = mutableListOf(),
        var itinerary: Itinerary?,
        //Usuarios que curtiram o post
        var favorited: MutableList<User?> = mutableListOf(),
        var author: Author,
        @JsonIgnore
        @Id
        var id: String? = null
) {
        fun favoritesCount() = favorited.size
}