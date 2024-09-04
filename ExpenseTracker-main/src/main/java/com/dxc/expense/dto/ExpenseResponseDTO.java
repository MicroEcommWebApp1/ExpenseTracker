package com.dxc.expense.dto;

import java.time.LocalDate;

public record ExpenseResponseDTO(
	    Integer expenseId,
	    Double amount,
	    String description,
	    String category,
	    LocalDate createdDate,
	    Integer userId,
	    byte[] receipt
	) {}
