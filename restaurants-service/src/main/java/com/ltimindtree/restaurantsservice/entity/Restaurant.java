package com.ltimindtree.restaurantsservice.entity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

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
@Table(name = "restaurant")
public class Restaurant {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "restaurant_id")
	private long restaurantId;

	@Column(name = "restaurant_id")
	private String restaurantName;

	@Embedded
	private Address address;

	@Builder.Default
	@ElementCollection
	@CollectionTable(name = "location_tag", joinColumns = @JoinColumn(name = "restaurant_id"))
	private Set<String> locationTag = new HashSet<>();

	@OneToMany(mappedBy = "restaurant")
	@JsonManagedReference
	private List<FoodItem> items;

	@Column(name = "budget")
	private int budget;

}
