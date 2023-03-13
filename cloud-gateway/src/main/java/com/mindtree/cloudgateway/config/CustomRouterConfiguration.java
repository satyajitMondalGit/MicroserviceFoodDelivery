package com.mindtree.cloudgateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomRouterConfiguration {

	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
	    return builder.routes()
	    		.route("1",r -> r.path("/api/v1/cart/**").uri("lb://cart-service"))
	    		.route("2",r -> r.path("/api/v1/delivery/**").uri("lb://delivery-service"))
	    		.route("3",r -> r.path("/api/v1/order/**").uri("lb://order-service"))
	    		.route("4",r -> r.path("/api/v1/restaurant/**").uri("lb://restaurant-service"))
	    		.route("5",r -> r.path("/api/v1/user/**").uri("lb://user-service"))
	    		.build();
	}
	
}
