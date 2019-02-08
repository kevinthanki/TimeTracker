package org.tracker.app.controller;

import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.tracker.app.entity.AccessToken;
import org.tracker.app.login.services.LoginService;

/**
 * 
 * @author kevin
 *
 */
@RestController
public class LoginController {

	@Autowired
	private LoginService loginService;

	public static final String COLLECTION_NAME = "accessToken";

	@PostMapping(path = "/login")
	public ResponseEntity<AccessToken> login(
			@RequestHeader(name = "username", required = true) @NotBlank String username,
			@RequestHeader(name = "password", required = true) @NotBlank String password) {
		AccessToken accessToken = loginService.doLogin(username, password);
		return new ResponseEntity<>(accessToken, HttpStatus.OK);
	}
}
