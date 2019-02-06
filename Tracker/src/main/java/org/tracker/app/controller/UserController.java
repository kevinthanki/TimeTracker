package org.tracker.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tracker.app.exception.UserAlreadyExistsException;
import org.tracker.app.request.AddUserRequest;
import org.tracker.app.user.services.AddUserService;

@RestController
@RequestMapping(path = "/user")
public class UserController {

	private AddUserService addUserService;

	public static final String COLLECTION_NAME = "user";

	@Autowired
	public UserController(AddUserService addUserService) {
		this.addUserService = addUserService;
	}

	@PostMapping(path = "/add")
	public ResponseEntity<Void> addUser(@RequestBody AddUserRequest addUserRequest,
			@RequestHeader(value = "username") String username, @RequestHeader(value = "password") String password)
			throws UserAlreadyExistsException {
		addUserService.addUser(addUserRequest, username, password);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
}
