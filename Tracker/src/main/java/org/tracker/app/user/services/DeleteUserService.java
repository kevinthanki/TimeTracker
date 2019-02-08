package org.tracker.app.user.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tracker.app.entity.User;
import org.tracker.app.exception.UserNotFoundException;
import org.tracker.app.repository.UserRepository;

@Service
public class DeleteUserService {

	@Autowired
	private UserRepository userRepository;

	public void deleteUser(String username) {
		Optional<User> user = userRepository.findById(username);
		if (user.isPresent()) {
			userRepository.delete(user.get());
		} else {
			throw new UserNotFoundException("User does not exist");
		}
	}
}
