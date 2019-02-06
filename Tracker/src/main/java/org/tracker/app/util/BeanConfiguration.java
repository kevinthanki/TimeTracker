package org.tracker.app.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.tracker.app.entity.Ticket;
import org.tracker.app.entity.User;

@Configuration
public class BeanConfiguration {

	@Value("${mongodb.hostname}")
	private String hostname;

	@Value("${mongodb.port}")
	private int port;

	@Bean
	public Ticket getTicketBean() {
		return new Ticket();
	}

	@Bean
	public User getUserBean() {
		return new User();
	}
}
