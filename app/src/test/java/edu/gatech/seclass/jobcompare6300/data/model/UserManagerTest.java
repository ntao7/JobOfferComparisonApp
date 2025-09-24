package edu.gatech.seclass.jobcompare6300.data.model;

import junit.framework.TestCase;

public class UserManagerTest extends TestCase {

    public void testGetInstance() {
        UserManager userManager = UserManager.getInstance();
        // Test the new UserManager is created.
        assertNotNull(userManager);
        // Test the new UserManager will not be created when it is not null.
        assertEquals(userManager, UserManager.getInstance());
    }

    public void testUser(){
        UserManager userManager = UserManager.getInstance();
        assertNull(userManager.getUser());
        User user = new User(1);
        user.setCurrentJob(new Job());
        userManager.setUser(user);
        assertEquals(userManager.getUser(), user);
    }
}