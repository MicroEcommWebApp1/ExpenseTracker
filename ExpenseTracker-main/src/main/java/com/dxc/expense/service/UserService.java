package com.dxc.expense.service;

import java.util.List;

import com.dxc.expense.dto.UserDTO;
import com.dxc.expense.dto.UserResponseDTO;

public interface UserService {

	public Integer createUser(UserDTO request);
	public List<UserResponseDTO> findAllUsers();
}
