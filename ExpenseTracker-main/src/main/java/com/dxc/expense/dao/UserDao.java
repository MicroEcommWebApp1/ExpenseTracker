package com.dxc.expense.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dxc.expense.model.User;

@Repository
public interface UserDao extends JpaRepository<User, Integer>{

}
