package com.dxc.expense.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dxc.expense.model.Budget;

@Repository
public interface BudgetDao extends JpaRepository<Budget, Integer> {
	
	@Query("SELECT b FROM Budget b WHERE b.user.id = :userId")
	List<Budget> findBudgetByUserId(@Param("userId") Integer userId);

}
