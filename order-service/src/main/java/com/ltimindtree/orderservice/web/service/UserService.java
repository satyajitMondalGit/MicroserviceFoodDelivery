package com.ltimindtree.orderservice.web.service;

import com.ltimindtree.orderservice.web.dto.AddressDTO;

public interface UserService {

	AddressDTO getAddressOfTheUser(long userId, long addressId);

}
