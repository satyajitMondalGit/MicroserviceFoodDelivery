package com.ltimindtree.userservice.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.ltimindtree.userservice.entity.User.UserBuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "Address")
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long addressId;
	private String type;
	private String address1;
	private String city;
	private String State;

	private int PIN;
	private String landMark;
	
	@ManyToOne
	@JsonBackReference
	private User user;
}
