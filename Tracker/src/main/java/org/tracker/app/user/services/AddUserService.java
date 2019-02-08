package org.tracker.app.user.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tracker.app.controller.UserController;
import org.tracker.app.entity.User;
import org.tracker.app.exception.UserAlreadyExistsException;
import org.tracker.app.repository.UserRepository;
import org.tracker.app.request.AddUserRequest;
import org.tracker.app.util.MongoUtil;

@Service
public class AddUserService {

	private UserRepository userRepository;
	private MongoUtil mongoUtil;

	@Autowired
	public AddUserService(UserRepository userRepository, MongoUtil mongoUtil) {
		this.userRepository = userRepository;
		this.mongoUtil = mongoUtil;
	}

	public void addUser(AddUserRequest addUserRequest, String username, String password)
			throws UserAlreadyExistsException {

		boolean exists = mongoUtil.checkIfRecordExists(UserController.COLLECTION_NAME, "_id", username);
		if (exists)
			throw new UserAlreadyExistsException("Username already exists");
		User user = new User();
		user.setName(addUserRequest.getName());
		user.setUsername(username);
		user.setPassword(password);
		userRepository.save(user);
	}
}
