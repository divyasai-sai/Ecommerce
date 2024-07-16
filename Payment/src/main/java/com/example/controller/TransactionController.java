package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Transaction;
import com.example.service.TransactionService;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

	@Autowired
	private TransactionService transactionService;

	@PostMapping("/done")
	public ResponseEntity<Transaction> createTransaction(@RequestBody Transaction transaction) {
		Transaction createdTransaction = transactionService.createTransaction(transaction);
		return ResponseEntity.ok(createdTransaction);
	}
}
