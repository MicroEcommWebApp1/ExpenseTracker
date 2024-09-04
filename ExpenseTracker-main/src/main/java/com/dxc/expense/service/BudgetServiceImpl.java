package com.dxc.expense.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dxc.expense.dao.BudgetDao;
import com.dxc.expense.dao.UserDao;
import com.dxc.expense.dto.BudgetDTO;
import com.dxc.expense.mapper.BudgetMapper;
import com.dxc.expense.model.Budget;
import com.dxc.expense.model.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BudgetServiceImpl implements BudgetService{
	
    private final BudgetDao budgetDao;
    private final UserDao userDao;
    private final BudgetMapper budgetMapper;

	@Override
	public BudgetDTO createBudget(BudgetDTO budgetDTO) {
        User user = userDao.findById(budgetDTO.userId())
            .orElseThrow(() -> new RuntimeException("User not found"));
        Budget budget = budgetMapper.toBudget(budgetDTO, user);
        Budget savedBudget = budgetDao.save(budget);
        return budgetMapper.toBudgetResponseDTO(savedBudget);
    }


	@Override
	public List<BudgetDTO> getBudgetsByUserId(Integer userId) {
        return budgetDao.findBudgetByUserId(userId).stream()
            .map(budgetMapper::toBudgetResponseDTO)
            .toList();
    }


	@Override
	public List<BudgetDTO> getAllBudget() {
	    
	    List<Budget> budgets = budgetDao.findAll();
	    
	    List<BudgetDTO> budgetDTOs = budgets.stream()
	        .map(budgetMapper::toBudgetResponseDTO)
	        .toList();

	    return budgetDTOs;
	}

	
	

}
