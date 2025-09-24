package edu.gatech.seclass.jobcompare6300.data.model;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

public class UserTest extends TestCase {

    public void testCurrentJob() {
        User user = new User(1);
        assertNull(user.getCurrentJob());
        // Job job = new Job("title1", "company1");
        // Update the way Job objects are created to match the new constructor
        Job job = new Job("title1", "company1", 10000f, 5000f, 2000);
        user.setCurrentJob(job);
        assertEquals(user.getCurrentJob(), job);
    }

    public void testJobs(){
        User user = new User(1);
        assertEquals(user.getJobs().size(), 0);
        List<Job> jobs = new ArrayList<Job>();
        // jobs.add(new Job("title1", "company1"));
        // jobs.add(new Job("title2", "company2"));
        // jobs.add(new Job("title3", "company3"));
        // Provide additional parameters for each Job object
        jobs.add(new Job("title1", "company1", 10000f, 5000f, 2000));
        jobs.add(new Job("title2", "company2", 20000f, 7000f, 3000));
        jobs.add(new Job("title3", "company3", 30000f, 8000f, 4000));
        user.setJobs(jobs);
        assertEquals(user.getJobs().size(), 3);
    }
}