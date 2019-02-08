package org.tracker.app.user.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tracker.app.entity.User;
import org.tracker.app.exception.UserNotFoundException;
import org.tracker.app.repository.UserRepository;

@Service
public class ListUsersService {

	@Autowired
	private UserRepository userRepository;

	public List<User> listUsers(String username) {
		if (username != null && !username.isEmpty()) {
			Optional<User> user = userRepository.findById(username);
			if (user.isPresent()) {
				List<User> users = new ArrayList<>();
				users.add(user.get());
				return users;
			} else {
				throw new UserNotFoundException("User does not exist");
			}
		} else {
			return userRepository.findAll();
		}
	}
}
