package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.CategorySales;
import com.example.repository.SalesRepository;

@Service
public class SalesService {

	@Autowired
	private SalesRepository salesRepository;

	public List<CategorySales> getSalesByCategory() {
		return salesRepository.findSalesByCategory();
	}
}