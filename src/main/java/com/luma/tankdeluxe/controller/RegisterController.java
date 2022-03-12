package com.luma.tankdeluxe.controller;

import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.luma.tankdeluxe.entity.User;
import com.luma.tankdeluxe.service.UserService;

@Controller
@RequestMapping("/register")
public class RegisterController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping
	public String getView() {
		return "register";
	}
	
	@PostMapping
	public String registerUser(RedirectAttributes redirectAttributes, HttpServletRequest request) {
		
		//On récupère les paramètres de la requête
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirm-password");
		String email = request.getParameter("email");
		
		//On vérifie qu'il n'y a pas d'erreur
		String errorMessage = checkRegisterParameters(username, password, confirmPassword, email);
		if(errorMessage != null) {
			redirectAttributes.addFlashAttribute("error", errorMessage);
			return "redirect:/register";
		}
		
		//On vérifie qu'il n'y a pas d'utilisateur de même nom
		User user = userService.find(username);
		if(user != null) {
			redirectAttributes.addFlashAttribute("error", "This username is already taken");
			return "redirect:/register";
		}
		
		//On crée l'utilisateur et on l'insère en base
		user = new User();
		user.setUuid(UUID.randomUUID());
		user.setLogin(username);
		user.setPassword(password);
		userService.createUser(user);
		
		//On connecte l'utilsiateur et on le redirige vers l'accueil
		try {
			request.login(username, password);
			return "redirect:/";
		} catch (ServletException e) {
			e.printStackTrace();
			return "redirect:/login";
		}
	}
	
	private String checkRegisterParameters(String username, String password, String confirmPassword, String email) {
		if(StringUtils.isBlank(username)) {
			return "Missing username";
		} else if(StringUtils.isBlank(password)) {
			return "Missing password";
		} else if(StringUtils.isBlank(confirmPassword)) {
			return "Missing password confirmation";
		} else if(StringUtils.isBlank(email)) {
			return "Missing email";
		} else if(!password.equals(confirmPassword)) {
			return "Passwords are not matching";
		}
		return null;
	}

}