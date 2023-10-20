package willydekeyser.config;

import java.util.Collections;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

public class CustomAuthenticationProvider implements AuthenticationProvider {
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		Assert.isInstanceOf(UsernamePasswordAuthenticationToken.class, authentication, "Fail");
		UsernamePasswordAuthenticationToken userToken = (UsernamePasswordAuthenticationToken) authentication;
		String username = userToken.getName();
		String password = (String) authentication.getCredentials();
		if (!StringUtils.hasLength(username)) {
			throw new BadCredentialsException("AuthenticationProvider Empty Username");
		}
		if (!StringUtils.hasLength(password)) {
			throw new BadCredentialsException("AuthenticationProvider Empty Password");
		}
		Assert.notNull(password, "Null password was supplied in authentication token");
		User user = null;
		if ("user".equals(username) && "password".equals(password)) {
			user = new User(username, password, Collections.emptySet());
		}
		return createSuccessfulAuthentication(userToken, user);
	}

	protected Authentication createSuccessfulAuthentication(UsernamePasswordAuthenticationToken authentication,
		UserDetails user) {
		Object password = user.getPassword();
		UsernamePasswordAuthenticationToken result = UsernamePasswordAuthenticationToken.authenticated(user, password,
			null);
		result.setDetails(authentication.getDetails());
		return result;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}
}
