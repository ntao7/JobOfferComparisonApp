package edu.gatech.seclass.jobcompare6300.db;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import edu.gatech.seclass.jobcompare6300.data.model.Job;
import edu.gatech.seclass.jobcompare6300.data.model.User;
import edu.gatech.seclass.jobcompare6300.ui.MainActivity;

@RunWith(AndroidJUnit4.class)
public class DBTest {
    @Rule
    //public ActivityScenarioRule<JobOfferActivity> activityRule = new ActivityScenarioRule<>(JobOfferActivity.class);
    public ActivityScenarioRule<MainActivity> activityRule = new ActivityScenarioRule<>(MainActivity.class);
    @Test
    public void testInsertJobOffer() {
        DBHelper dbHelper = DBHelper.getInstance(InstrumentationRegistry.getInstrumentation().getTargetContext(), 1);
        Job job = new Job();
        job.setTitle("Software Engineer");
        job.setCompany("Google");
        job.setCity("Mountain View, CA");
        job.setCost(1000);
        job.setSalary(120000f);
        job.setBonus(10000f);
        job.setOption(5000f);
        job.setHbp(2000f);
        job.setHoliday(15);
        job.setStipend(50f);

        long jobId = dbHelper.insertJobOffer(job);
        assertTrue(jobId > 0);
    }


    @Test
    public void testInsertUser() {
        DBHelper dbHelper = DBHelper.getInstance(InstrumentationRegistry.getInstrumentation().getTargetContext(),1);
        long userId = dbHelper.insertUser();
        assertTrue("User ID should be greater than 0", userId > 0);
    }

    @Test
    public void testGetAllJobsByUserId() {
        DBHelper dbHelper = DBHelper.getInstance(InstrumentationRegistry.getInstrumentation().getTargetContext(), 1);
        long userId = 1;
        for (int i = 0; i < 3; i++) {
            Job job = new Job();
            job.setUserId(userId);
            job.setTitle("Job Title " + i);
            dbHelper.insertJobOffer(job);
        }
        List<Job> jobs = dbHelper.getJobListByUserId(userId);
        assertNotNull("Jobs list should not be null", jobs);
        assertTrue("Jobs list should contain at least 3 jobs", jobs.size() >= 3);
        assertEquals("First job's title should match", "Job Title 0", jobs.get(0).getTitle());
    }

    @Test
    public void insertAndRetrieveJobListByUserIdTest() {
        DBHelper dbHelper = DBHelper.getInstance(InstrumentationRegistry.getInstrumentation().getTargetContext(), 1);
        long userId = dbHelper.insertUser();
        assertTrue(userId > 0);

        for (int i = 0; i < 5; i++) {
            Job job = new Job();
            job.setUserId(userId);
            job.setTitle("Software Engineer " + i);
            job.setCompany("Company " + i);
            job.setCity("City " + i);
            job.setCost(1000 + i);
            job.setSalary(50000F + i);
            job.setOption(100F + i);
            job.setHbp(200F + i);
            job.setHoliday(20 + i);
            job.setStipend(3000F + i);
            long jobId = dbHelper.insertJobOffer(job);
            assertTrue(jobId > 0);
        }

        List<Job> jobs = dbHelper.getJobListByUserId(userId);
        assertNotNull(jobs);
        assertEquals(5, jobs.size());
        for (int i = 0; i < jobs.size(); i++) {
            Job job = jobs.get(i);
            assertEquals("Software Engineer " + i, job.getTitle());
            assertEquals("Company " + i, job.getCompany());
        }
    }

    @Test
    public void updateUserCurrentJobIdTest() {
        DBHelper dbHelper = DBHelper.getInstance(InstrumentationRegistry.getInstrumentation().getTargetContext(), 1);

        long userId = dbHelper.insertUser();
        assertTrue(userId > 0);

        Job job = new Job();
        job.setUserId(userId);
        job.setTitle("Senior Developer");
        job.setCompany("Innovative Tech");
        job.setCity("Innovation City");
        job.setCost(1500);
        job.setSalary(70000F);
        job.setBonus(7000F);
        job.setOption(150F);
        job.setHbp(250F);
        job.setHoliday(25);
        job.setStipend(4000F);

        long jobId = dbHelper.insertJobOffer(job);
        assertTrue(jobId > 0);
        long updateCount = dbHelper.updateUserCurrentJobId(userId, jobId);
        assertEquals(1, updateCount);
        Job currentJob = dbHelper.getCurrentJobByUserId(userId);
        assertNotNull(currentJob);
        assertEquals("Senior Developer", currentJob.getTitle());
    }









}
