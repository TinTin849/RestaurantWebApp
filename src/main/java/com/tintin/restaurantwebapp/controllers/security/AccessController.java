package com.tintin.restaurantwebapp.controllers.security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class AccessController {

    @RequestMapping("/access-denied")
    public String accessDenied() {
        return "auth/access-denied";
    }
}
