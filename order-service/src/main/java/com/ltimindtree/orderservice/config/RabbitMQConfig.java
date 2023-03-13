package com.ltimindtree.orderservice.config;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ltimindtree.orderservice.constants.Constants;

@Configuration
public class RabbitMQConfig {

	@Bean
	public Queue queueOrderRestaurant() {
		return new Queue(Constants.ORDER_RESTAURANTS_QUEUE);
	}

	@Bean
	public Queue queueOrderDelivery() {
		return new Queue(Constants.ORDER_DELIVERY_QUEUE);
	}

	@Bean
	public DirectExchange exchange() {
		return new DirectExchange(Constants.ORDER_SERVICE_EXCHANGE);
	}

	@Bean
	public Binding orderRestaurantBinding() {
		return BindingBuilder.bind(queueOrderRestaurant()).to(exchange()).with(Constants.ORDER_RESTAURANTS_ROUTING_KEY);
	}

	@Bean
	public Binding orderDeliveryBinding() {
		return BindingBuilder.bind(queueOrderDelivery()).to(exchange()).with(Constants.ORDER_DELIVERY_ROUTING_KEY);
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
	
//	@Bean(name = "pimAmqpAdmin")
//    public AmqpAdmin pimAmqpAdmin(@Qualifier("defaultConnectionFactory") ConnectionFactory connectionFactory) {
//        return new RabbitAdmin(connectionFactory);
//    }
}
