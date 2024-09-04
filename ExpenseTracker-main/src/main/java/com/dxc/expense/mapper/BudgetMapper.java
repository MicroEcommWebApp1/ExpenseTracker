package com.dxc.expense.mapper;

import org.springframework.stereotype.Service;

import com.dxc.expense.dto.BudgetDTO;
import com.dxc.expense.model.Budget;
import com.dxc.expense.model.User;

@Service
public class BudgetMapper {

	public Budget toBudget(BudgetDTO request, User user) {
        return Budget.builder()
            .amount(request.amount())
            .startDate(request.startDate())
            .endDate(request.endDate())
            .user(user)
            .build();
    }

    public BudgetDTO toBudgetResponseDTO(Budget budget) {
        return new BudgetDTO(
        		budget.getAmount(),
                budget.getStartDate(),
                budget.getEndDate(),
                budget.getUser().getId()
        	);
    }
}
