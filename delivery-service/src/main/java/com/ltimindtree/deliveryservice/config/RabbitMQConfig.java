package com.ltimindtree.deliveryservice.config;

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

import com.ltimindtree.deliveryservice.constants.Constants;



@Configuration
public class RabbitMQConfig {

	@Bean
	public Queue queueDeliveryRestaurant() {
		return new Queue(Constants.DELIVERY_RESTAURANTS_QUEUE);
	}

	@Bean
	public Queue queueDeliveryOrder() {
		return new Queue(Constants.DELIVERY_ORDER_QUEUE);
	}

	@Bean
	public DirectExchange exchange() {
		return new DirectExchange(Constants.DELIVERY_SERVICE_EXCHANGE);
	}

	@Bean
	public Binding orderRestaurantBinding() {
		return BindingBuilder.bind(queueDeliveryRestaurant()).to(exchange()).with(Constants.DELIVERY_RESTAURANTS_KEY);
	}

	@Bean
	public Binding orderDeliveryBinding() {
		return BindingBuilder.bind(queueDeliveryOrder()).to(exchange()).with(Constants.DELIVERY_ORDER_KEY);
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
