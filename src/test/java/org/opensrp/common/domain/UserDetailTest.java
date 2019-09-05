package org.opensrp.common.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

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
		UserDetail userDetail = new UserDetail("real", rolesList);
		userDetail.setPreferredName("preferredName");
		assertEquals("real", userDetail.getUserName());
		assertNotSame("peal", userDetail.getUserName());
		assertEquals("TLI", userDetail.getRoles().get(0));
		assertNotSame("TLI", userDetail.getRoles().get(1));
		assertEquals("preferredName", userDetail.getPreferredName());
	}
}
