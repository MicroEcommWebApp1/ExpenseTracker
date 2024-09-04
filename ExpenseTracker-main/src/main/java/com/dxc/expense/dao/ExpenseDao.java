package com.dxc.expense.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dxc.expense.dto.ExpenseResponseDTO;
import com.dxc.expense.model.Expense;

@Repository
public interface ExpenseDao extends JpaRepository<Expense, Integer> {
	
	@Query("SELECT e FROM Expense e WHERE e.user.id = :userId")
	List<Expense> findExpensesByUserId(@Param("userId") Integer userId);
	
}
