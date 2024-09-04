package com.dxc.expense.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record ExpenseRequestDTO(
	    @NotNull(message = "Amount cannot be null")
	    @Min(value = 0, message = "Amount must be a positive number")
	    Double amount,

	    @NotBlank(message = "Description cannot be blank")
	    @Size(max = 255, message = "Description cannot exceed 255 characters")
	    String description,

	    @NotBlank(message = "Category cannot be blank")
	    @Size(max = 50, message = "Category cannot exceed 50 characters")
	    String category,
	    
	    LocalDate createdDate,

	    @NotNull(message = "User ID cannot be null")
	    Integer userId,
	    
	    byte[] receipt
	) {}
