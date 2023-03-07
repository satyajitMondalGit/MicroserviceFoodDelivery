package com.ltimindtree.userservice.web.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ltimindtree.userservice.entity.Address;
import com.ltimindtree.userservice.entity.User;
import com.ltimindtree.userservice.exception.AddressNotFoundException;
import com.ltimindtree.userservice.exception.UserNotFoundException;
import com.ltimindtree.userservice.repository.AddressRepository;
import com.ltimindtree.userservice.repository.UserRepository;
import com.ltimindtree.userservice.web.dto.AddressDTO;
import com.ltimindtree.userservice.web.dto.UserDTO;
import com.ltimindtree.userservice.web.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private AddressRepository addressRepo;

	@Override
	public UserDTO addUser(UserDTO userDto) {

		User user = userRepo.save(new User((long) 0, userDto.getUserName(), userDto.getEmail(), userDto.getMobileNo(), null));

		
		List<AddressDTO> addDto = userDto.getAddressDto();

		List<Address> add = addDto.stream()
				.map(a -> addressRepo.save(Address.builder().type(a.getType()).address1(a.getAddress1())
						.city(a.getCity()).State(a.getState()).PIN(a.getPIN()).landMark(a.getLandMark()).user(user).build()))
				.collect(Collectors.toList());

	
		
		return UserDTO.builder().userId(user.getUserId()).userName(user.getUserName()).email(user.getEmail())
				.mobileNo(user.getMobileNo())
				.addressDto(add.stream()
						.map(a -> AddressDTO.builder().addressId(a.getAddressId()).type(a.getType())
								.address1(a.getAddress1()).city(a.getCity()).State(a.getState()).PIN(a.getPIN())
								.landMark(a.getLandMark()).build())
						.collect(Collectors.toList()))
				.build();
	}

	@Override
	public UserDTO getUser(long userId) {
		User user = userRepo.findById(userId).orElseThrow(()->new UserNotFoundException("User is not found with Id "+userId));
		return UserDTO.builder().userId(user.getUserId()).userName(user.getUserName()).email(user.getEmail())
				.mobileNo(user.getMobileNo())
				.addressDto(user.getAddress().stream()
						.map(a -> AddressDTO.builder().addressId(a.getAddressId()).type(a.getType())
								.address1(a.getAddress1()).city(a.getCity()).State(a.getState()).PIN(a.getPIN())
								.landMark(a.getLandMark()).build())
						.collect(Collectors.toList()))
				.build();
	}

	@Override
	public AddressDTO getAddress(long userId, long addId) {
		User user = userRepo.findById(userId).orElseThrow(()->new UserNotFoundException("User is not found with Id "+userId));
		//Address a = addressRepo.findById(addId).orElseThrow(()->new AddressNotFoundException("Address is not found with Id "+addId));
		Optional<Address> address = user.getAddress().stream().filter(a ->a.getAddressId()==addId).findAny();
		if(!address.isPresent()) {
			throw new AddressNotFoundException("Address is not found with Id "+addId);
		}
		Address a = address.get();
		return AddressDTO.builder().addressId(a.getAddressId()).type(a.getType())
				.address1(a.getAddress1()).city(a.getCity()).State(a.getState()).PIN(a.getPIN())
				.landMark(a.getLandMark()).build();
	}

}
