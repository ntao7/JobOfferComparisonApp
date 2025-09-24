package edu.gatech.seclass.jobcompare6300.data.model;

import java.util.HashMap;
import java.util.Map;

public class Weights {
    private Long userId;
    private int salary=1;
    private int bonus=1;
    private int option=1;
    private int hbp=1;
    private int holiday=1;
    private int stipend=1;

    public Weights(Long userId) {
        this.userId = userId;
    }

    public Weights(Long userId, int salary, int bonus, int option, int hbp, int holiday, int stipend) {
        this.userId = userId;
        this.salary = salary;
        this.bonus = bonus;
        this.option = option;
        this.hbp = hbp;
        this.holiday = holiday;
        this.stipend = stipend;
    }

    public Long getUserId() {
        return userId;
    }

    public int getSalary() {
        return salary;
    }

    public int getBonus() {
        return bonus;
    }

    public int getOption() {
        return option;
    }

    public int getHbp() {
        return hbp;
    }

    public int getHoliday() {
        return holiday;
    }

    public int getStipend() {
        return stipend;
    }

    public Map<String, Integer> toMap() {
        Map<String, Integer> weights = new HashMap<>();
        weights.put("salary", salary);
        weights.put("bonus", bonus);
        weights.put("option", option);
        weights.put("hbp", hbp);
        weights.put("holiday", holiday);
        weights.put("stipend", stipend);
        return weights;
    }
}