package edu.gatech.seclass.jobcompare6300.util;

import junit.framework.TestCase;

public class JobComparatorManagerTest extends TestCase {

    public void testGetInstance() {
        JobComparatorManager manager = JobComparatorManager.getInstance();
        // Test the new JobComparatorManager is created.
        assertNotNull(manager);
        // Test the new JobComparatorManager will not be created when it is not null.
        assertEquals(manager, JobComparatorManager.getInstance());
    }

    public void testJobComparator(){
        JobComparatorManager manager = JobComparatorManager.getInstance();
        assertNull(manager.getJobComparator());
        JobComparator jobComparator = new JobComparator();
        manager.setJobComparator(jobComparator);
        assertNotNull(manager.getJobComparator());
    }
}