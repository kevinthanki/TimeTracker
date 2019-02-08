package org.tracker.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tracker.app.entity.User;
import org.tracker.app.exception.UserAlreadyExistsException;
import org.tracker.app.request.AddUserRequest;
import org.tracker.app.user.services.AddUserService;
import org.tracker.app.user.services.DeleteUserService;
import org.tracker.app.user.services.ListUsersService;

/**
 * 
 * @author kevin
 *
 */
@RestController
@RequestMapping(path = "/user")
public class UserController {

	private AddUserService addUserService;
	private ListUsersService listUsersService;
	private DeleteUserService deleteUserService;
	public static final String COLLECTION_NAME = "user";

	@Autowired
	public UserController(AddUserService addUserService, ListUsersService listUsersService,
			DeleteUserService deleteUserService) {
		this.addUserService = addUserService;
		this.listUsersService = listUsersService;
		this.deleteUserService = deleteUserService;
	}

	@PostMapping
	public ResponseEntity<Void> addUser(@RequestBody AddUserRequest addUserRequest,
			@RequestHeader(value = "username") String username, @RequestHeader(value = "password") String password)
			throws UserAlreadyExistsException {
		addUserService.addUser(addUserRequest, username, password);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@GetMapping(path = { "/", "", "/{username}" })
	public ResponseEntity<List<User>> listUsers(
			@PathVariable(name = "username", value = "username", required = false) String username) {
		List<User> users = listUsersService.listUsers(username);
		if (users.isEmpty()) {
			return new ResponseEntity<>(users, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(users, HttpStatus.OK);
		}
	}

	@DeleteMapping(path = "/{username}")
	public ResponseEntity<Void> deleteUser(
			@PathVariable(name = "username", value = "username", required = true) String username) {
		deleteUserService.deleteUser(username);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
