package com.dxc.expense.mapper;

import org.springframework.stereotype.Service;

import com.dxc.expense.dto.UserDTO;
import com.dxc.expense.dto.UserResponseDTO;
import com.dxc.expense.model.User;

@Service
public class UserMapper {
	
	public User toUser(UserDTO user) {
        return User.builder()
        		.id(user.id())
                .firstName(user.firstName())
                .lastName(user.lastName())
                .email(user.email())
                .password(user.password())
                .build();
    }
	
	public UserResponseDTO fromUser(User user) {
		return new UserResponseDTO(
				user.getId(),
				user.getFirstName(),
				user.getLastName(),
				user.getEmail()
			);
	}

}
