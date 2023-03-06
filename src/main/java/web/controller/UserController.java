package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import web.model.User;
import web.service.UserDetailsServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("")
public class UserController {
	private UserDetailsServiceImpl userDetailsService;
	private Optional<String> optionalUser;
	@Autowired
	public UserController(UserDetailsServiceImpl userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	@GetMapping(value = "/login")
	public String getLoginPage() {

		return "login";
	}

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public String getUserPage() {
		return "user";
	}

	@GetMapping(value = "/admin")
	public String getAdminPage() {
		return "admin";
	}
}