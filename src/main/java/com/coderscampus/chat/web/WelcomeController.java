package com.coderscampus.chat.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.servlet.http.HttpSession;

@Controller
@SessionAttributes("name")
public class WelcomeController {

	@GetMapping("/welcome")
	public String welcomePage(ModelMap model, HttpSession session) {
		String currentUserName = (String) session.getAttribute("name");
		model.put("name", currentUserName);
		return "welcome";
	}

	@PostMapping("/setUsername")
	public String setUsername(@RequestParam String username, ModelMap model) {
		if (username != null && !username.trim().isEmpty()) {
			model.put("name", username);
			return "redirect:/welcome";
		}
		return "redirect:/welcome";
	}

}