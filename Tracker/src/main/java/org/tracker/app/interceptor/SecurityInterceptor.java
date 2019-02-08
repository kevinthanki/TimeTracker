package org.tracker.app.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.tracker.app.exception.InvalidTokenException;
import org.tracker.app.login.services.LoginService;

/**
 * 
 * @author kevin
 *
 */
@Component
public class SecurityInterceptor implements HandlerInterceptor {

	@Autowired
	private LoginService loginService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		String accessToken = request.getHeader("access-token");
		if (accessToken == null || accessToken.isEmpty())
			throw new InvalidTokenException("Access Token not found");

		boolean isValidToken = loginService.isTokenValid(accessToken);

		if (!isValidToken)
			throw new InvalidTokenException("Either the token is invalid or expired");

		return HandlerInterceptor.super.preHandle(request, response, handler);
	}
}
