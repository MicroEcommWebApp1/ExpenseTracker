package com.dxc.expense.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.dxc.expense.dao.UserDao;
import com.dxc.expense.dto.UserDTO;
import com.dxc.expense.dto.UserResponseDTO;
import com.dxc.expense.mapper.UserMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
	
	private final UserDao userDao;
	private final UserMapper mapper;

	@Override
	public Integer createUser(UserDTO request) {
		var user = userDao.save(mapper.toUser(request));
		return user.getId();
	}

	@Override
	public List<UserResponseDTO> findAllUsers() {
		return userDao.findAll()
				.stream()
				.map(mapper::fromUser)
				.collect(Collectors.toList());
	}

}
