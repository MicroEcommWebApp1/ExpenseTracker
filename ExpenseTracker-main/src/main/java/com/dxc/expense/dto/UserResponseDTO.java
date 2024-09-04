package com.dxc.expense.dto;

public record UserResponseDTO(
		Integer id,
		String firstName,
		String lastName,
		String email
) {}
