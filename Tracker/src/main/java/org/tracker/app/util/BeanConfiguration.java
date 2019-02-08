package org.tracker.app.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.tracker.app.entity.AccessToken;
import org.tracker.app.entity.Ticket;
import org.tracker.app.entity.User;

@Configuration
public class BeanConfiguration {

	@Bean
	public Ticket getTicketBean() {
		return new Ticket();
	}

	@Bean
	public User getUserBean() {
		return new User();
	}

	@Bean
	public AccessToken token() {
		return new AccessToken();
	}
}
