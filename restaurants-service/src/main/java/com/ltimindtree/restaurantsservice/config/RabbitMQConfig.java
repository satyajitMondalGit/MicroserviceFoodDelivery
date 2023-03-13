package com.ltimindtree.restaurantsservice.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ltimindtree.restaurantsservice.constants.Constants;



@Configuration
public class RabbitMQConfig {

	@Bean
	public Queue queueOrderRestaurant() {
		return new Queue(Constants.RESTAURANTS_ORDER_QUEUE);
	}

	
	@Bean
	public DirectExchange exchange() {
		return new DirectExchange(Constants.RESTAURANTS_SERVICE_EXCHANGE);
	}

	@Bean
	public Binding orderRestaurantBinding() {
		return BindingBuilder.bind(queueOrderRestaurant()).to(exchange()).with(Constants.RESTAURANTS_ORDER_ROUTING_KEY);
	}

	

	@Bean
	public MessageConverter converter() {
		return new Jackson2JsonMessageConverter();
	}

	@Bean
	public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory) {
		RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(converter());
		return rabbitTemplate;
	}

}
