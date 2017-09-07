package de.norbertspiess.spring.boot.oauth;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;
import java.util.Arrays;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;


public class SpringSecurityVersionIndependentUUID extends ObjectInputStream {
	private static final String userAuthorityComparator = User.class.getName() + "$AuthorityComparator";

	SpringSecurityVersionIndependentUUID(byte[] bytes) throws IOException {
		super(new ByteArrayInputStream(bytes));
	}

	@Override
	protected ObjectStreamClass readClassDescriptor() throws IOException, ClassNotFoundException {
		ObjectStreamClass resultClassDescriptor = super.readClassDescriptor();

		if (descriptorEquals(resultClassDescriptor, UsernamePasswordAuthenticationToken.class.getName())) {
			resultClassDescriptor = lookup(UsernamePasswordAuthenticationToken.class);
		} else if (descriptorEquals(resultClassDescriptor, SimpleGrantedAuthority.class.getName())) {
			resultClassDescriptor = lookup(SimpleGrantedAuthority.class);
		} else if (descriptorEquals(resultClassDescriptor, User.class.getName())) {
			resultClassDescriptor = lookup(User.class);
		} else if (descriptorEquals(resultClassDescriptor, userAuthorityComparator)) {
			resultClassDescriptor = lookup(getUserAuthorityComparatorClass());
		}
		return resultClassDescriptor;
	}

	private boolean descriptorEquals(ObjectStreamClass resultClassDescriptor, String className) {
		return resultClassDescriptor.getName().equals(className);
	}

	private ObjectStreamClass lookup(Class clazz) {
		return ObjectStreamClass.lookup(clazz);
	}

	private Class<?> getUserAuthorityComparatorClass() {
		/* hack to get the internal UserAuthorityComparator class definition. If the User class ever changes on this,
		 this reference has to be found again.*/
		Class<?>[] privateClassesInUser = User.class.getDeclaredClasses();
		return Arrays.stream(privateClassesInUser)
				.filter(clazz -> clazz.getName().equals(userAuthorityComparator))
				.findFirst().orElseGet(null);
	}
}
