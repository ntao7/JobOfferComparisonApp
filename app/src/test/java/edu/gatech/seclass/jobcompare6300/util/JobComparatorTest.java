package edu.gatech.seclass.jobcompare6300.util;

import junit.framework.TestCase;

import java.util.HashMap;
import java.util.Map;

import edu.gatech.seclass.jobcompare6300.data.model.Job;

public class JobComparatorTest extends TestCase {

    public void testSetWeights() {
        JobComparator comparator = new JobComparator();
        Map<String, Integer> weights= new HashMap<String, Integer>() {{
            put("salary", 1);
            put("bonus", 1);
            put("option", 1);
            put("hbp", 1);
            put("holiday", 1);
            put("stipend", 1);
        }};
        // Without setting the weight, the weight is the default value.
        assertEquals(comparator.getWeights(), weights);
        weights.put("salary", 10);
        // After setting the weight, the weight is changed.
        comparator.setWeights(weights);
        assertEquals((int)comparator.getWeights().get("salary"), 10);
    }

    public void testComputeScore() {
        JobComparator comparator = new JobComparator();
        long user_id = 1;
        String title = "title1";
        String company = "company1";
        String city = "city1";
        String state = "state1";
        int cost = 1;
        float salary = 2;
        float bonus = 3;
        float option = 4;
        float hbp = 5;
        int holiday = 6;
        float stipend = 7;
        Job job1 = new Job(user_id, title, company, city, state, cost, salary, bonus, option, hbp, holiday, stipend);

        title = "title2";
        company = "company2";
        city = "city2";
        state = "state2";
        cost = 10;
        salary = 20;
        bonus = 30;
        option = 40;
        hbp = 50;
        holiday = 60;
        stipend = 70;
        Job job2 = new Job(user_id, title, company, city, state, cost, salary, bonus, option, hbp, holiday, stipend);

        // The job2 has higher scores than job1.
        assertEquals(comparator.compare(job2, job1), 1);
    }
}