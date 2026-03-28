package com.example.oauth2_client.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "home"; //
    }

    @GetMapping("/profile")
    public String profile(Model model, @AuthenticationPrincipal OAuth2User user) {
        if (user == null) {
            return "redirect:/";
        }


        String name = user.getAttribute("name");
        String email = user.getAttribute("email");


        if (name == null) {
            name = user.getAttribute("preferred_username");
        }


        String picture = user.getAttribute("picture");


        if (picture == null || picture.isEmpty()) {

            picture = "https://ui-avatars.com/api/?name=" + (name != null ? name.replace(" ", "+") : "User");
        }

        model.addAttribute("name", name);
        model.addAttribute("email", email);
        model.addAttribute("picture", picture);

        return "profile";
    }
    @GetMapping("/logout-success")
    public String logoutSuccess() {
        return "logout";
    }
}