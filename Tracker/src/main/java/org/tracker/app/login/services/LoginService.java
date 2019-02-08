package org.tracker.app.login.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.tracker.app.controller.LoginController;
import org.tracker.app.entity.AccessToken;
import org.tracker.app.entity.User;
import org.tracker.app.exception.InvalidCredentialsException;
import org.tracker.app.repository.AccessTokenRepository;
import org.tracker.app.repository.UserRepository;
import org.tracker.app.util.MongoUtil;

/**
 * 
 * @author kevin
 *
 */
@Service
public class LoginService {

	private UserRepository userRepository;
	private AccessTokenRepository accessTokenRepository;
	private MongoUtil mongoUtil;
	private AccessToken token;

	@Value("${login.session.timeout}")
	private long sessionTimeout;

	@Autowired
	public LoginService(UserRepository userRepository, AccessTokenRepository accessTokenRepository, MongoUtil mongoUtil,
			AccessToken token) {
		this.userRepository = userRepository;
		this.accessTokenRepository = accessTokenRepository;
		this.mongoUtil = mongoUtil;
		this.token = token;
	}

	public AccessToken doLogin(String username, String password) {
		Optional<User> user = userRepository.findById(username);
		if (user.isPresent()) {
			if (user.get().getPassword().equals(password)) {

				AccessToken activeSessionToken = isSessionActive(username);
				if (activeSessionToken != null && activeSessionToken.isActiveSession()) {
					activeSessionToken.setLastAccessed(System.nanoTime());
					activeSessionToken = accessTokenRepository.save(activeSessionToken);
					return activeSessionToken;
				} else {
					long curTime = System.nanoTime();
					token.setCreated(curTime);
					token.setLastAccessed(curTime);
					token.setUsername(username);
					token.setActiveSession(true);
					token.setTokenId(requestToken());
					token = accessTokenRepository.save(token);
					return token;
				}
			} else {
				throw new InvalidCredentialsException("Invalid credentials");
			}
		} else {
			throw new InvalidCredentialsException("Invalid credentials");
		}
	}

	private AccessToken isSessionActive(String username) {
		List<Object> tokens = mongoUtil.findWithCriteria(LoginController.COLLECTION_NAME, "username", username,
				AccessToken.class);
		if (!tokens.isEmpty()) {
			for (Object obj : tokens) {
				AccessToken accessToken = (AccessToken) obj;
				if (accessToken.isActiveSession()) {
					long diff = System.nanoTime() - accessToken.getLastAccessed();

					if (diff > (1e+9 * sessionTimeout)) {
						accessToken.setActiveSession(false);
					} else {
						accessToken.setLastAccessed(System.nanoTime());
					}
					accessTokenRepository.save(accessToken);
					return accessToken;
				}
			}
		}
		return null;
	}

	public AccessToken isSessionActive(AccessToken accessToken) {
		if (accessToken.isActiveSession()) {
			long diff = System.nanoTime() - accessToken.getLastAccessed();
			if (diff > (1e+9 * sessionTimeout)) {
				accessToken.setActiveSession(false);
				accessToken = accessTokenRepository.save(accessToken);
			}
		}
		return accessToken;
	}

	public boolean isTokenValid(String token) {
		boolean exists = mongoUtil.checkIfRecordExists(LoginController.COLLECTION_NAME, "_id", token);
		if (exists) {
			Optional<AccessToken> accessToken = accessTokenRepository.findById(token);
			if (accessToken.isPresent()) {
				return isSessionActive(accessToken.get()).isActiveSession();
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	private String requestToken() {
		String tokenId = UUID.randomUUID().toString();
		tokenId = tokenId.replace("-", "").toUpperCase();
		return tokenId;
	}
}
