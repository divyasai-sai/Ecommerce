//package com.example.service;
//
//import java.nio.charset.StandardCharsets;
//import java.security.MessageDigest;
//import java.security.NoSuchAlgorithmException;
//import java.time.LocalDateTime;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.example.model.Transaction;
//import com.example.model.User;
//import com.example.repository.TransactionRepository;
//
//@Service
//public class TransactionService {
//
//	@Autowired
//	private TransactionRepository transactionRepository;
//
//	@Autowired
//	private EmailService emailService;
//
//	public Transaction createTransaction(Transaction transaction) {
////		 checkStockAvailability(transaction.getCartItems()); 
//		validateTransaction(transaction);
//
//		// Hash cardNumber and upiId using SHA-256
//		if (transaction.getCardNumber() != null) {
//			transaction.setCardNumber(hashWithSHA256(transaction.getCardNumber()));
//		}
//		if (transaction.getUpiId() != null) {
//			transaction.setUpiId(hashWithSHA256(transaction.getUpiId()));
//		}
//
//		transaction.setPaymentDate(LocalDateTime.now());
//		sendConfirmationEmail(user);
//		return transactionRepository.save(transaction);
//	}
//
//	private void validateTransaction(Transaction transaction) {
//		if (transaction.getPaymentMethod() == null || transaction.getPaymentMethod().isEmpty()) {
//			throw new IllegalArgumentException("Payment method is required");
//		}
//
//		String paymentMethod = transaction.getPaymentMethod().toUpperCase();
//		if (!paymentMethod.equals("CARD") && !paymentMethod.equals("UPI") && !paymentMethod.equals("COD")) {
//			throw new IllegalArgumentException("Invalid payment method");
//		}
//
//		switch (paymentMethod) {
//		case "CARD":
//			validateCardNumber(transaction.getCardNumber());
//			break;
//		case "UPI":
//			validateUpiId(transaction.getUpiId());
//			break;
//		case "COD":
//			transaction.setCardNumber(null);
//			transaction.setUpiId(null);
//			break;
//		}
//	}
//
//	private void validateCardNumber(String cardNumber) {
//		if (cardNumber == null || !cardNumber.matches("\\d{16}")) {
//			throw new IllegalArgumentException("Invalid card number");
//		}
//	}
//
//	private void validateUpiId(String upiId) {
//		if (upiId == null || !upiId.matches("^[a-zA-Z0-9.\\-_]{2,256}@[a-zA-Z]{2,64}$")) {
//			throw new IllegalArgumentException("Invalid UPI ID");
//		}
//	}
////	private void checkStockAvailability(List<CartItem> cartItems) {
////        for (CartItem cartItem : cartItems) {
////            ProductVariant productVariant = cartItem.getProductVariant();
////            if (productVariant.getStock() < cartItem.getQuantity()) {
////                String productName = productVariant.getProduct().getName();
////                throw new IllegalArgumentException("Insufficient stock for product: " + productName);
////            }
////        }
////    }
//
//	// Utility method to hash a string with SHA-256
//	private String hashWithSHA256(String input) {
//		try {
//			MessageDigest digest = MessageDigest.getInstance("SHA-256");
//			byte[] hash = digest.digest(input.getBytes(StandardCharsets.UTF_8));
//			StringBuilder hexString = new StringBuilder();
//
//			for (byte b : hash) {
//				String hex = String.format("%02x", b);
//				hexString.append(hex);
//			}
//
//			return hexString.toString();
//		} catch (NoSuchAlgorithmException e) {
//			e.printStackTrace();
//			return null; // Handle appropriately in your application
//		}
//	}
//
//	private void sendConfirmationEmail(User user) {
//		String to = user.getEmail();
//		String subject = "Order Confirmed !!";
//		String text = String.format(
//				"Dear %s,\n\nYour order has been successfully placed.\n\nThanks for choosing us.\n\nBest Regards,\nFlexiFit",
//				user.getFirstName(), user.getEmail());
//		emailService.sendEmail(to, subject, text);
//	}
//}

package com.example.service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Transaction;
import com.example.model.User;
import com.example.repository.TransactionRepository;
import com.example.repository.UserRepository;

@Service
public class TransactionService {

	@Autowired
	private TransactionRepository transactionRepository;

	@Autowired
	private EmailService emailService;

	@Autowired
	private UserRepository userRepository;

	public Transaction createTransaction(Transaction transaction) {
		validateTransaction(transaction);

		// Hash cardNumber and upiId using SHA-256
		if (transaction.getCardNumber() != null) {
			transaction.setCardNumber(hashWithSHA256(transaction.getCardNumber()));
		}
		if (transaction.getUpiId() != null) {
			transaction.setUpiId(hashWithSHA256(transaction.getUpiId()));
		}

		transaction.setPaymentDate(LocalDateTime.now());

		Transaction savedTransaction = transactionRepository.save(transaction);

		// Fetch user email and send confirmation email
		User user = userRepository.findById(transaction.getUserId())
				.orElseThrow(() -> new IllegalArgumentException("User not found"));
		sendConfirmationEmail(user);

		return savedTransaction;
	}

	private void validateTransaction(Transaction transaction) {
		if (transaction.getPaymentMethod() == null || transaction.getPaymentMethod().isEmpty()) {
			throw new IllegalArgumentException("Payment method is required");
		}

		String paymentMethod = transaction.getPaymentMethod().toUpperCase();
		if (!paymentMethod.equals("CARD") && !paymentMethod.equals("UPI") && !paymentMethod.equals("COD")) {
			throw new IllegalArgumentException("Invalid payment method");
		}

		switch (paymentMethod) {
		case "CARD":
			validateCardNumber(transaction.getCardNumber());
			break;
		case "UPI":
			validateUpiId(transaction.getUpiId());
			break;
		case "COD":
			transaction.setCardNumber(null);
			transaction.setUpiId(null);
			break;
		}
	}

	private void validateCardNumber(String cardNumber) {
		if (cardNumber == null || !cardNumber.matches("\\d{16}")) {
			throw new IllegalArgumentException("Invalid card number");
		}
	}

	private void validateUpiId(String upiId) {
		if (upiId == null || !upiId.matches("^[a-zA-Z0-9.\\-_]{2,256}@[a-zA-Z]{2,64}$")) {
			throw new IllegalArgumentException("Invalid UPI ID");
		}
	}

	// Utility method to hash a string with SHA-256
	private String hashWithSHA256(String input) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(input.getBytes(StandardCharsets.UTF_8));
			StringBuilder hexString = new StringBuilder();

			for (byte b : hash) {
				String hex = String.format("%02x", b);
				hexString.append(hex);
			}

			return hexString.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null; // Handle appropriately in your application
		}
	}

	private void sendConfirmationEmail(User user) {
		String to = user.getEmail();
		String subject = "Order Confirmed !!";
		String text = String.format(
				"Dear %s,\n\nYour order has been successfully placed.\n\nThanks for choosing us.\n\nBest Regards,\nFlexiFit",
				user.getFirstName());
		emailService.sendEmail(to, subject, text);
	}
}
