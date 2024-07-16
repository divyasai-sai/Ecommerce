package com.example.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.User;

import com.example.service.UserService;
import jakarta.validation.Valid;

@RestController

@RequestMapping("/auth")


public class AuthController {

	@Autowired
	private UserService userService;

	@PostMapping("/signup")
	public ResponseEntity<?> signUp(@Valid @RequestBody User user) {
		try {
			user.setRole("User"); // Set the role as "USER" by default
			return ResponseEntity.ok(userService.saveUser(user));

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Validation error: " + e.getMessage());

		}

	}

	@PostMapping("/login")

	public ResponseEntity<Map<String, String>> login(@RequestBody Map<String, String> request) {

		String email = request.get("email");

		String password = request.get("password");

		User user = userService.findByEmail(email);

		if (user != null && userService.authenticate(email, password)) {

			Map<String, String> response = new HashMap<>();

			response.put("message", "Login successful");

			response.put("email", email);

			response.put("role", user.getRole()); // Include the role in the response

			response.put("id", user.getId().toString());
			response.put("name", user.getFirstName());

			return ResponseEntity.ok(response);

		}

		Map<String, String> errorResponse = new HashMap<>();

		errorResponse.put("message", "Invalid credentials");

		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);

	}

	@PostMapping("/forgot-password")

	public ResponseEntity<String> forgotPassword(@RequestBody Map<String, String> request) {

		String email = request.get("email");

		String securityQuestion = request.get("securityQuestion");

		String securityAnswer = request.get("securityAnswer");

		boolean isValid = userService.checkSecurityAnswer(email, securityQuestion, securityAnswer);

		if (isValid) {

			return ResponseEntity.ok("Security question and answer match. Please proceed to reset your password.");

		}

		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Security question and answer do not match.");

	}

	@PostMapping("/reset-password")

	public ResponseEntity<String> resetPassword(@RequestBody Map<String, String> request) {

		String email = request.get("email");

		String newPassword = request.get("newPassword");

		User existingUser = userService.findByEmail(email);

		if (existingUser != null) {

			userService.updatePassword(existingUser, newPassword);

			return ResponseEntity.ok("Password updated successfully.");

		}

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");

	}

	@PostMapping("/check-email")

	public ResponseEntity<Map<String, Boolean>> checkEmail(@RequestBody Map<String, String> request) {

		String email = request.get("email");

		boolean exists = userService.emailExists(email);

		return ResponseEntity.ok(Collections.singletonMap("exists", exists));

	}

	
	@GetMapping("/users/{id}")
	public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
		User user = userService.findUserById(id);
		if (user != null) {
			return ResponseEntity.ok(user);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}

	@PatchMapping(value = "/users/{id}/phone")
	public ResponseEntity<User> updateUserPhone(@PathVariable("id") Long id, @RequestBody Map<String, String> updates) {
		if (updates.containsKey("phoneNo")) {
			User updatedUser = userService.updateUserPhone(id, updates.get("phoneNo"));
			return ResponseEntity.ok(updatedUser);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	}

}
