package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.model.User;
import com.example.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private EmailService emailService;

	public User saveUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setSecurityAnswer(bCryptPasswordEncoder.encode(user.getSecurityAnswer()));
		return userRepository.save(user);
	}

	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	public void updatePassword(User user, String newPassword) {
		user.setPassword(bCryptPasswordEncoder.encode(newPassword));
		userRepository.save(user);
		sendPasswordResetEmail(user, newPassword);
	}

	public boolean authenticate(String email, String plainPassword) {
		User user = findByEmail(email);
		return user != null && bCryptPasswordEncoder.matches(plainPassword, user.getPassword());
	}

	public boolean checkSecurityAnswer(String email, String securityQuestion, String securityAnswer) {
		User user = findByEmail(email);
		if (user != null) {
			boolean questionMatches = user.getSecurityQuestion().equals(securityQuestion);
			boolean answerMatches = bCryptPasswordEncoder.matches(securityAnswer, user.getSecurityAnswer());
			return questionMatches && answerMatches;
		}
		return false;
	}

	public boolean emailExists(String email) {
		return userRepository.findByEmail(email) != null;
	}

	private void sendPasswordResetEmail(User user, String newPassword) {
		String to = user.getEmail();
		String subject = "Password Updated Successfully";
		String text = String.format(
				"Dear %s,\n\nYour password has been successfully reset.\n\nYour Email: %s\nYour New Password: %s\n\nBest Regards,\nFlexiFit",
				user.getFirstName(), user.getEmail(), newPassword);
		emailService.sendEmail(to, subject, text);
	}
	public User updateUserPhone(Long id, String phoneNo) {
		User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
		user.setPhoneNo(phoneNo);
		return userRepository.save(user);
	}
 
	public User findUserById(Long id) {
		return userRepository.findById(id).orElse(null);
	}

}
