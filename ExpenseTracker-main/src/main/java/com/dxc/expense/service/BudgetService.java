package com.dxc.expense.service;

import java.util.List;

import com.dxc.expense.dto.BudgetDTO;

public interface BudgetService {
	
	BudgetDTO createBudget(BudgetDTO budgetDTO);

    List<BudgetDTO> getBudgetsByUserId(Integer userId);
    
    List<BudgetDTO> getAllBudget();

}
