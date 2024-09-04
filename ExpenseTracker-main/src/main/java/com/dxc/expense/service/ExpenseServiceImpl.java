package com.dxc.expense.service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.dxc.expense.dao.ExpenseDao;
import com.dxc.expense.dao.UserDao;
import com.dxc.expense.dto.ExpenseRequestDTO;
import com.dxc.expense.dto.ExpenseResponseDTO;
import com.dxc.expense.exception.ResourceNotFoundException;
import com.dxc.expense.mapper.ExpenseMapper;
import com.dxc.expense.model.Expense;
import com.dxc.expense.model.User;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExpenseServiceImpl implements ExpenseService {
	
    private final ExpenseDao expenseDao;
    private final UserDao userDao;
    private final ExpenseMapper expenseMapper;
    
    public ExpenseResponseDTO createExpense(ExpenseRequestDTO requestDTO) throws IOException {
        User user = userDao.findById(requestDTO.userId())
                                  .orElseThrow(() -> new RuntimeException("User not found"));

        Expense expense = Expense.builder()
                                 .amount(requestDTO.amount())
                                 .description(requestDTO.description())
                                 .category(requestDTO.category())
                                 .createdDate(requestDTO.createdDate() != null ? requestDTO.createdDate() : LocalDate.now())
                                 .receipt(requestDTO.receipt() != null ? requestDTO.receipt() : null)
                                 .user(user)
                                 .build();

        Expense savedExpense = expenseDao.save(expense);
        return expenseMapper.toExpenseResponseDTO(savedExpense);
    }

	@Override
    public ExpenseResponseDTO getExpenseById(Integer expenseId) {
        Expense expense = expenseDao.findById(expenseId)
                .orElseThrow(() -> new ResourceNotFoundException("Expense not found with id: " + expenseId));
        return expenseMapper.toExpenseResponseDTO(expense);
    }

	@Override
	public ExpenseResponseDTO updateExpense(Integer expenseId, ExpenseRequestDTO request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteExpense(Integer expenseId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Transactional
    public List<ExpenseResponseDTO> getAllExpensesByUserId(Integer userId) {
        
        userDao.findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        List<Expense> expenses = expenseDao.findExpensesByUserId(userId);

        return expenses.stream()
                .map(expenseMapper::toExpenseResponseDTO)
                .collect(Collectors.toList());
    }

	@Override
    public List<ExpenseResponseDTO> getAllExpenses() {
        List<Expense> expenses = expenseDao.findAll();
        return expenses.stream()
                .map(expenseMapper::toExpenseResponseDTO)
                .collect(Collectors.toList());
    }

	@Override
	public byte[] downloadReceipt(Integer expenseId) {
		Expense expense = expenseDao.findById(expenseId)
                .orElseThrow(() -> new ResourceNotFoundException("Expense not found with id: " + expenseId));
        return expense.getReceipt();
	}

}
