package com.dxc.expense.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dxc.expense.dto.ExpenseRequestDTO;
import com.dxc.expense.dto.ExpenseResponseDTO;
import com.dxc.expense.exception.ResourceNotFoundException;
import com.dxc.expense.service.ExpenseServiceImpl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/expense")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200/")
public class ExpenseController {

	private final ExpenseServiceImpl expenseService;
	
	@PostMapping
    public ResponseEntity<ExpenseResponseDTO> createExpense(
            @Valid @RequestParam("amount") Double amount,
            @Valid @RequestParam("description") String description,
            @Valid @RequestParam("category") String category,
            @RequestParam(value = "createdDate", required = false) LocalDate createdDate,
            @Valid @RequestParam("userId") Integer userId,
            @RequestParam(value = "receipt", required = false) MultipartFile receipt) throws IOException {
        try {
            ExpenseRequestDTO expenseRequestDTO = ExpenseRequestDTO.builder()
            		.amount(amount)
            		.description(description)
            		.category(category)
            		.createdDate(createdDate)
            		.userId(userId)
            		.receipt(receipt.getBytes())
            		.build();
            
            ExpenseResponseDTO createdExpense = expenseService.createExpense(expenseRequestDTO);
            return new ResponseEntity<>(createdExpense, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
	
	@GetMapping("/{expenseId}/receipt")
	public ResponseEntity<byte[]> downloadReceipt(@PathVariable Integer expenseId) {
	    try {
	        ExpenseResponseDTO expenseResponse = expenseService.getExpenseById(expenseId);

	        if (expenseResponse.receipt() == null || expenseResponse.receipt().length == 0) {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }

	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_PDF);
	        headers.setContentDisposition(ContentDisposition.attachment().filename("receipt-" + expenseId + ".pdf").build());

	        return new ResponseEntity<>(expenseResponse.receipt(), headers, HttpStatus.OK);
	    } catch (ResourceNotFoundException e) {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    } catch (Exception e) {
	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	
	@GetMapping
    public ResponseEntity<List<ExpenseResponseDTO>> getAllExpenses() {
        try {
            List<ExpenseResponseDTO> response = expenseService.getAllExpenses();
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
	@GetMapping("/{expenseId}")
    public ResponseEntity<ExpenseResponseDTO> getExpenseById(@PathVariable Integer expenseId) {
        try {
            ExpenseResponseDTO response = expenseService.getExpenseById(expenseId);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
	@GetMapping("/user/{userId}")
    public ResponseEntity<List<ExpenseResponseDTO>> getAllExpensesByUserId(@PathVariable Integer userId) {
        try {
            List<ExpenseResponseDTO> response = expenseService.getAllExpensesByUserId(userId);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
        	e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
}
