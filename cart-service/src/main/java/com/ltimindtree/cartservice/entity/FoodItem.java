package com.ltimindtree.cartservice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

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
@Table(name = "food_item")
public class FoodItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private int itemId;
	
	@Column(name = "item_name")
	private String itemName;

	@Column(name = "item_category")
	private String category;

	@Column(name = "cuisine")
	private String cuisine;

	private int price;

	@Column(name = "ordered_qty")
	private int quentity;


	@ManyToOne
	@JoinColumn(name = "cart_Id", nullable = false)
	@JsonBackReference
	private Cart cart;

}
