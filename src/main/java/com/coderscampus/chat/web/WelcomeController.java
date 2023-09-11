package com.coderscampus.chat.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.coderscampus.chat.domain.User;
import com.coderscampus.chat.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@SessionAttributes("name")
public class WelcomeController {

	@Autowired
	UserService userService;

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

			User existingUser = userService.findByUsername(username);

			if (existingUser == null) {
				// User does not exist, so create and save the new user
				User newUser = new User();
				newUser.setUsername(username);
				userService.save(newUser);
			} else {
				// User exists, so you might update or just leave it as it is.
				// For this example, we're not doing anything if the user exists.
			}

			return "redirect:/welcome";
		}
		return "redirect:/welcome";
	}

}