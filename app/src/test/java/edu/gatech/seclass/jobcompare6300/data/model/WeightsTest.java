package edu.gatech.seclass.jobcompare6300.data.model;

import junit.framework.TestCase;

import java.util.HashMap;
import java.util.Map;

public class WeightsTest extends TestCase {

    public void testGetters() {
        Long userId = 1L;
        int salary = 2;
        int bonus = 3;
        int option = 4;
        int hbp = 5;
        int holiday = 6;
        int stipend = 7;

        Weights weights = new Weights(userId, salary, bonus, option, hbp, holiday, stipend);

        assertEquals((long)weights.getUserId(), (long)userId);
        assertEquals(weights.getSalary(), salary);
        assertEquals(weights.getBonus(), bonus);
        assertEquals(weights.getOption(), option);
        assertEquals(weights.getHbp(), hbp);
        assertEquals(weights.getHoliday(), holiday);
        assertEquals(weights.getStipend(), stipend);
    }

    public void testToMap() {
        Long userId = 1L;
        int salary = 2;
        int bonus = 3;
        int option = 4;
        int hbp = 5;
        int holiday = 6;
        int stipend = 7;

        Weights weights = new Weights(userId, salary, bonus, option, hbp, holiday, stipend);

        Map<String, Integer> expectedWeightsMap = new HashMap<>();
        expectedWeightsMap.put("salary", salary);
        expectedWeightsMap.put("bonus", bonus);
        expectedWeightsMap.put("option", option);
        expectedWeightsMap.put("hbp", hbp);
        expectedWeightsMap.put("holiday", holiday);
        expectedWeightsMap.put("stipend", stipend);

        assertEquals(weights.toMap(), expectedWeightsMap);
    }
}

