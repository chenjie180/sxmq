package com.example.demo.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class DeadConfig {
	@Bean
	public Queue testDeadQueue() {
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("x-dead-letter-exchange", "DeadExchange");
		params.put("x-dead-letter-routing-key", "DeadRouting");
		return new Queue("TestDeadQueue",true,false,false,params);
				
	}
	
	
	@Bean
	public DirectExchange testDeadExchange() {
		return new DirectExchange("TestDeadExchange");
	}
	
	@Bean
	public Binding bindingTestDeadQueue() {
		return BindingBuilder.bind(testDeadQueue()).to(testDeadExchange()).with("TestDeadRouting");
		
	}
	
	
	
	
	@Bean
	public Queue deadQueue() {
		Map<String, Object> params=new HashMap<String, Object>();
		return new Queue("DeadQueue",true);
				
	}
	
	
	@Bean
	public DirectExchange deadExchange() {
		return new DirectExchange("DeadExchange");
	}
	
	@Bean
	public Binding bindingDeadQueue() {
		return BindingBuilder.bind(deadQueue()).to(deadExchange()).with("DeadRouting");
		
	}

}
