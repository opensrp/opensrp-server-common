package org.opensrp.common.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * Created by real on 12/07/17.
 */
public class UserDetailTest {
	
	@Test
	public void testConstructorNGetters() {
		List<String> rolesList = new ArrayList<>();
		rolesList.add("TLI");
		rolesList.add("FD");
		UserDetail userDetail = UserDetail.builder().userName("real").roles(rolesList).identifier("123")
		        .email("john@doe.com").familyName("Doe").givenName("John").emailVerified(true).build();
		userDetail.setPreferredName("preferredName");
		assertEquals("real", userDetail.getUserName());
		assertNotSame("peal", userDetail.getUserName());
		assertEquals("TLI", userDetail.getRoles().get(0));
		assertNotSame("TLI", userDetail.getRoles().get(1));
		assertEquals("preferredName", userDetail.getPreferredName());
		assertEquals("123", userDetail.getIdentifier());
		assertEquals("john@doe.com", userDetail.getEmail());
		assertEquals("Doe", userDetail.getFamilyName());
		assertEquals("John", userDetail.getGivenName());
		assertTrue(userDetail.isEmailVerified());
	}
}
