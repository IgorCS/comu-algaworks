package com.algaworks.financeiro.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Dinesh Rajput
 * 
 */
@Controller
public class AdminController {

	@RequestMapping(value = "/403.xhtml", method = RequestMethod.GET)
	public String accessDenied(ModelMap model, Principal principal) {
		String username = principal.getName();
		model.addAttribute("message", "Sorry " + username + " You don't have privileges to view this page!!!");
		return "403";
	}

	/*
	 * @RequestMapping(value = "/admin", method = RequestMethod.GET) public
	 * String welcomeAdmin(ModelMap model, Principal principal) { String
	 * username = principal.getName(); model.addAttribute("author", username);
	 * model.addAttribute("message", "Hello Spring Security - ADMIN PAGE");
	 * return "welcome";
	 * 
	 * }
	 * 
	 * @RequestMapping(value = "/index", method = RequestMethod.GET) public
	 * String printMessage(ModelMap model, Principal principal) {
	 * 
	 * String username = principal.getName(); model.addAttribute("author",
	 * username); model.addAttribute("message",
	 * "Hello Spring Security - USER LOGIN"); return "welcome"; }
	 */

}
