package edu.gatech.seclass.jobcompare6300.data.model;

import junit.framework.TestCase;

public class JobTest extends TestCase {

/*    public void testTestToString() {
        Job job = new Job("title1", "company1");
        assertEquals(job.toString(), "Job{title=\'title1\', company=\'company1\'}");
    }*/
    public void testTestToString() {
        // Assuming reasonable test values are provided for salary, bonus, and cost
        float testSalary = 10000;
        float testBonus = 5000;
        int testCost = 2000;
        Job job = new Job("title1", "company1", testSalary, testBonus, testCost);
        assertEquals(job.toString(), "Job{title='title1', company='company1'}");
    }

    public void testBasicFunctions() {
        long user_id = 1;
        String title = "title1";
        String company = "company1";
        String city = "city";
        String state = "state";
        int cost = 1;
        float salary = 2;
        float bonus = 3;
        float option = 4;
        float hbp = 5;
        int holiday = 6;
        float stipend = 7;
        Job job = new Job(user_id, title, company, city, state, cost, salary, bonus, option, hbp, holiday, stipend);
        assertEquals(job.getTitle(), title);
        assertEquals(job.getCompany(), company);
        assertEquals(job.getCity(), city);
        assertEquals(job.getState(), state);
        assertEquals((int)job.getCost(), cost);
        assertEquals(job.getSalary(), salary);
        assertEquals(job.getBonus(), bonus);
        assertEquals(job.getOption(), option);
        assertEquals(job.getHbp(), hbp);
        assertEquals((int)job.getHoliday(), holiday);
        assertEquals(job.getStipend(), stipend);

        float new_salary = 10;
        job.setSalary(new_salary);
        assertNotSame(job.getSalary(), salary);
        assertEquals(job.getSalary(), new_salary);
    }
}