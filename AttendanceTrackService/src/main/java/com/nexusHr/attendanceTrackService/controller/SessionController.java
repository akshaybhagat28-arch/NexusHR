package com.nexusHr.attendanceTrackService.controller;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/session")
public class SessionController {


	@PostMapping("/set")
	public String setSession(HttpSession session, @RequestParam String username) {

		session.setAttribute("user", username);
		return "Session stored in Redis";
	}

	@GetMapping("/get")
	public String getSession(HttpSession session) {

		String user = (String) session.getAttribute("user");

		return user != null ? user : "No session found";
	}

	@GetMapping("/invalidate")
	public String clear(HttpSession session) {
		session.invalidate();
		return "Session cleared";
	}
}