package de.norbertspiess.spring.boot.oauth;

import java.io.IOException;
import java.io.ObjectInputStream;

import javax.sql.DataSource;

import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;


/**
 * This is an extended JdbcTokenStore.
 * It was introduced because the deserialization of oauth tokens is using a serial id that is fixed per spring security
 * version. Thus the deserialization failed if spring security was updated.
 * The class was taken and adapted from
 * <a href=https://github.com/spring-projects/spring-security-oauth/issues/535#issuecomment-245230025>github</a>.
 *
 */
public class SpringSecurityVersionIndependentJdbcTokenStore extends JdbcTokenStore {
	public SpringSecurityVersionIndependentJdbcTokenStore(DataSource dataSource) {
		super(dataSource);
	}

	@Override
	protected OAuth2Authentication deserializeAuthentication(byte[] authentication) {
		return deserialize(authentication);
	}

	@Override
	protected OAuth2AccessToken deserializeAccessToken(byte[] token) {
		return deserialize(token);
	}

	@Override
	protected OAuth2RefreshToken deserializeRefreshToken(byte[] token) {
		return deserialize(token);
	}

	@SuppressWarnings("unchecked")
	private <T> T deserialize(byte[] authentication) {
		try {
			return (T) super.deserializeAuthentication(authentication);
		} catch (Exception e) {
			try (ObjectInputStream input = new SpringSecurityVersionIndependentUUID(authentication)) {
				return (T) input.readObject();
			} catch (IOException | ClassNotFoundException e1) {
				throw new IllegalArgumentException(e1);
			}
		}
	}
}