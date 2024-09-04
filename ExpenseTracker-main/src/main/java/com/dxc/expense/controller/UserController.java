package com.dxc.expense.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dxc.expense.dto.UserDTO;
import com.dxc.expense.dto.UserResponseDTO;
import com.dxc.expense.service.UserServiceImpl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200/")
public class UserController {
	
	private final UserServiceImpl service;
	
	@PostMapping
	public ResponseEntity<Integer> createUser(@RequestBody @Valid UserDTO request) {
		return ResponseEntity.ok(service.createUser(request));
	}
	
	@GetMapping
	public ResponseEntity<List<UserResponseDTO>> findAll() {
		return ResponseEntity.ok(service.findAllUsers());
	}
}
