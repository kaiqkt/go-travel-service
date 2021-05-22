package com.gotravel.application.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

@RestController
@RequestMapping("/healthcheck")
class HController {

    @GetMapping
    fun healthCheck(): LocalDateTime = LocalDateTime.now()
}