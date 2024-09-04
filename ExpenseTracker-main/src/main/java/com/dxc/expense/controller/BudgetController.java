package com.dxc.expense.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dxc.expense.dto.BudgetDTO;
import com.dxc.expense.service.BudgetServiceImpl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/budget")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200/")
public class BudgetController {

	private final BudgetServiceImpl budgetService;
	
	@PostMapping
    public ResponseEntity<BudgetDTO> createBudget(@Valid @RequestBody BudgetDTO budgetDTO) {
		try {
            BudgetDTO createdBudget = budgetService.createBudget(budgetDTO);
            return new ResponseEntity<>(createdBudget, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }    
	}
	
	@GetMapping("/user/{userId}")
    public ResponseEntity<List<BudgetDTO>> getBudgetsByUser(@PathVariable Integer userId) {
        try {
            List<BudgetDTO> budgets = budgetService.getBudgetsByUserId(userId);
            return new ResponseEntity<>(budgets, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
	@GetMapping
	public ResponseEntity<List<BudgetDTO>> getAllBudgets() {
	    try {
	        List<BudgetDTO> budgets = budgetService.getAllBudget();
	        return new ResponseEntity<>(budgets, HttpStatus.OK);
	    } catch (Exception e) {
	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}

}
