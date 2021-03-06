package com.gotravel.application.controller

import com.gotravel.application.dto.Profile
import com.gotravel.application.response.Response
import com.gotravel.domain.entities.User
import com.gotravel.domain.repositories.UserRepository
import com.gotravel.domain.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/profile")
class ProfileController(val userService: UserService, val userRepository: UserRepository) {

    @GetMapping("/{username}")
    fun profile(@PathVariable username: String): ResponseEntity<Response<Any>>{
        val response: Response<Any> = Response<Any>()

        userService.findByUsername(username)?.let {
            response.data = view(it, userService.currentUser()!!)
            return ResponseEntity.ok(response)
        }
        return ResponseEntity.notFound().build()
    }

    @PostMapping("/{username}/follow")
    fun follow(@PathVariable username: String): ResponseEntity<Response<Any>>{
        val response: Response<Any> = Response<Any>()

        userService.findByUsername(username)?.let {
            var currentUser = userService.currentUser()
            if (!currentUser?.follows?.contains(it)!!) {
                currentUser?.follows.add(it)
                var currentUserAtt = userRepository.save(currentUser)
            }
            response.data = view(it, currentUser)
            return ResponseEntity.ok(response)
        }
        return ResponseEntity.notFound().build()
    }

    @DeleteMapping("/{username}/follow")
    fun unfollow(@PathVariable username: String): ResponseEntity<Response<Any>>{
        val response: Response<Any> = Response<Any>()

        userService.findByUsername(username)?.let {
            var currentUser = userService.currentUser()
            if (currentUser?.follows?.contains(it)!!) {
                currentUser?.follows.remove(it)
                var currentUserAtt = userRepository.save(currentUser)
            }
            response.data = view(it, currentUser)
            return ResponseEntity.ok(response)
        }
        return ResponseEntity.notFound().build()
    }

    fun view(user: User, currentUser: User) = mapOf("profile" to Profile.fromUser(user, currentUser, user.image))
}