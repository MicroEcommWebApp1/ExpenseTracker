package com.dxc.expense.service;

import java.io.IOException;
import java.util.List;

import com.dxc.expense.dto.ExpenseRequestDTO;
import com.dxc.expense.dto.ExpenseResponseDTO;

public interface ExpenseService {
	
    public ExpenseResponseDTO createExpense(ExpenseRequestDTO request) throws IOException;
    
    public ExpenseResponseDTO getExpenseById(Integer expenseId);
    
    public ExpenseResponseDTO updateExpense(Integer expenseId, ExpenseRequestDTO request);
    
    public void deleteExpense(Integer expenseId);
    
    public List<ExpenseResponseDTO> getAllExpensesByUserId(Integer userId);
    
    public List<ExpenseResponseDTO> getAllExpenses();

	public byte[] downloadReceipt(Integer expenseId);
}
