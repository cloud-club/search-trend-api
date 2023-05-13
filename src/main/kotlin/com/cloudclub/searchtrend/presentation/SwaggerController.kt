package com.cloudclub.searchtrend.presentation

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class SwaggerController {
    @GetMapping("/swagger")
    fun swagger() = "redirect:/swagger-ui/index.html"
}
