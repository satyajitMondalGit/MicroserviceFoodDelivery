package com.ltimindtree.userservice.web.service;

import com.ltimindtree.userservice.web.dto.AddressDTO;
import com.ltimindtree.userservice.web.dto.UserDTO;

public interface UserService {

	UserDTO addUser(UserDTO userDto);

	UserDTO getUser(long id);

	AddressDTO getAddress(long userId, long addId);

}
