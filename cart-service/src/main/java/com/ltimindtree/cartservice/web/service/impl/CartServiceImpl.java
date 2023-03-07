package com.ltimindtree.cartservice.web.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ltimindtree.cartservice.entity.Cart;
import com.ltimindtree.cartservice.entity.FoodItem;
import com.ltimindtree.cartservice.exception.FoodItemNotFound;
import com.ltimindtree.cartservice.exception.ItemAlreadyExistException;
import com.ltimindtree.cartservice.exception.UserNotFoundException;
import com.ltimindtree.cartservice.repository.CartRepository;
import com.ltimindtree.cartservice.repository.FoodRepository;
import com.ltimindtree.cartservice.web.dto.CartDTO;
import com.ltimindtree.cartservice.web.dto.FoodItemDTO;
import com.ltimindtree.cartservice.web.dto.ResponseCartDTO;
import com.ltimindtree.cartservice.web.service.CartService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CartServiceImpl implements CartService {

	private CartRepository cartRepo;
	private FoodRepository foodRepo;

	public CartServiceImpl(CartRepository cartRepo, FoodRepository foodRepo) {
		super();
		this.cartRepo = cartRepo;
		this.foodRepo = foodRepo;
	}

	@Override
	public ResponseCartDTO addItemInTheCart(CartDTO cartDto) {
		Optional<Cart> cart = cartRepo.findbyUserId(cartDto.getUserId());
		Cart cartFinal = new Cart(); 
		
		if (cart.isPresent()) {
			
			List<FoodItem> allFood = foodRepo.findbyUserId(cartDto.getUserId());
		//	.orElseThrow(()->new FoodItemNotFound("Food Item Not Found"));

			
			List<Integer> itemset= allFood.stream().map(f->f.getItemId()).collect(Collectors.toList());
			

			List<FoodItem> fList = cartDto.getFoodItem().stream()
					.map(f -> {
						if(itemset != null && itemset.contains(f.getItemId())) {
							throw new ItemAlreadyExistException("Item is already exits");
						}else {
							System.out.println("itemset"+itemset.size());
							FoodItem f1= FoodItem.builder().itemId(f.getItemId()).itemName(f.getItemName())
									.category(f.getCategory()).cuisine(f.getCuisine()).price(f.getPrice())
									.quentity(f.getQuentity()).cart(cart.get()).build();

							return foodRepo.save(f1);
						}})
					.collect(Collectors.toList());
if(fList ==null) {throw new ItemAlreadyExistException("Item is already exits");}
			
			
			
			allFood.addAll(fList);
			cartFinal.setFooditem( allFood);
			cartFinal.setCartId(cart.get().getCartId());
			cartFinal.setRestaurantId(cart.get().getRestaurantId());
			cartFinal.setUserId(cart.get().getUserId());
			cartFinal.setTotalPrice(allFood.stream().map(f -> f.getPrice() * f.getQuentity()).reduce(0, Integer::sum));
			
			cartRepo.save(cartFinal);
		} else {
			Cart cart1 = Cart
					.builder().userId((long)cartDto.getUserId()).restaurantId((long)cartDto.getResturantId()).totalPrice(cartDto
							.getFoodItem().stream().map(f -> f.getPrice() * f.getQuentity()).reduce(0, Integer::sum))
					.build();
			Cart cartNew = cartRepo.save(cart1);

			List<FoodItem> fList = cartDto.getFoodItem().stream()
					.map(f -> FoodItem.builder().itemId(f.getItemId()).itemName(f.getItemName())
							.category(f.getCategory()).cuisine(f.getCuisine()).price(f.getPrice())
							.quentity(f.getQuentity()).cart(cartNew).build())
					.map(f -> foodRepo.save(f)).collect(Collectors.toList());

			cartFinal.setFooditem(fList);
			cartFinal.setCartId(cartNew.getCartId());
			cartFinal.setRestaurantId(cartNew.getRestaurantId());
			cartFinal.setUserId(cartNew.getUserId());
			cartFinal.setTotalPrice(cartNew.getTotalPrice());
			
		}
		

		return ResponseCartDTO.builder().userId(cartFinal.getUserId()).restaurantId(cartFinal.getCartId())
				.fooditems(cartDto.getFoodItem()).totalPrice(cartFinal.getTotalPrice()).build();
	}

	@Override
	public ResponseCartDTO updateItemOfTheCart(CartDTO cartDto) {
		Optional<Cart> cart = cartRepo.findbyUserId(cartDto.getUserId());
		int totalPrice = 0; 
		Cart cartFinal = new Cart(); 
		if (cart.isPresent()) {
			totalPrice = cart.get().getTotalPrice();
			List<FoodItem> allFood = foodRepo.findbyUserId(cartDto.getUserId());

			Map<Integer, FoodItem> item= allFood.stream().collect(Collectors.toMap(FoodItem::getItemId, Function.identity()));
			Set<FoodItem> fList = cartDto.getFoodItem().stream()
					.map(f -> {
						if((item.containsKey(f.getItemId()))) {
							FoodItem f1 = item.get(f.getItemId());
							f1.setQuentity(f.getQuentity());
							
							return (FoodItem)foodRepo.save(f1);
						}else {
							throw new FoodItemNotFound("Item is not added earlier. Please add it first");
						}})
					.collect(Collectors.toSet());
			
			 allFood = foodRepo.findbyUserId(cartDto.getUserId());
			cartFinal.setFooditem(allFood);
			cartFinal.setCartId(cart.get().getCartId());
			cartFinal.setRestaurantId(cart.get().getRestaurantId());
			cartFinal.setUserId(cart.get().getUserId());
			cartFinal.setTotalPrice(allFood.stream().map(f -> f.getPrice() * f.getQuentity()).reduce(0, Integer::sum));
			
			cartRepo.save(cartFinal);
		}else {
			throw new UserNotFoundException("No Item Found for this user");
		}
		return ResponseCartDTO.builder().userId(cartFinal.getUserId()).restaurantId(cartFinal.getCartId())
				.fooditems(cartDto.getFoodItem()).totalPrice(cartFinal.getTotalPrice()).build();
	}

	@Override
	public String deleteItemFromTheCart(CartDTO cartDto) {
		Optional<Cart> cart = cartRepo.findbyUserId(cartDto.getUserId());
		Cart cartFinal = new Cart(); 
		if (cart.isPresent()) {
			List<FoodItem> allFood = foodRepo.findbyUserId(cartDto.getUserId());

			Map<Integer, FoodItem> item= allFood.stream().collect(Collectors.toMap(FoodItem::getItemId, Function.identity()));
			Set<FoodItem> fList = cartDto.getFoodItem().stream()
					.filter(f -> item.containsKey(f.getItemId()))
					.map(f->item.get(f.getItemId()))
					.collect(Collectors.toSet());
			
			if(allFood.size()==fList.size()) {
				foodRepo.deleteAll(fList);
				cartRepo.delete(cart.get());
			}else {
				foodRepo.deleteAll(fList);
				allFood.removeAll(fList);
			}
			
			
			cartFinal.setFooditem(allFood);
			cartFinal.setCartId(cart.get().getCartId());
			cartFinal.setRestaurantId(cart.get().getRestaurantId());
			cartFinal.setUserId(cart.get().getUserId());
			cartFinal.setTotalPrice(allFood.stream().map(f -> f.getPrice() * f.getQuentity()).reduce(0, Integer::sum));
			
			cartRepo.save(cartFinal);
		}else {
			throw new UserNotFoundException("No Item Found for this user");
		}
		return "Item Deleted Successfully";
	}

	@Override
	public ResponseCartDTO getCartDetails(long userId) {
		Cart cart = cartRepo.findbyUserId(userId).orElseThrow(()->new UserNotFoundException("No Item Found for this user"));
		List<FoodItem> allFood = foodRepo.findbyUserId(userId);
		cart.setFooditem(allFood);
		
		return ResponseCartDTO.builder().userId(cart.getUserId()).restaurantId(cart.getCartId())
				.fooditems(cart.getFooditem().stream()
						.map(f -> FoodItemDTO.builder().itemId(f.getItemId()).itemName(f.getItemName())
								.category(f.getCategory()).cuisine(f.getCuisine()).price(f.getPrice())
								.quentity(f.getQuentity()).build())
						.collect(Collectors.toList()))
				.totalPrice(cart.getTotalPrice()).build();
	}

}
