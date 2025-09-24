package edu.gatech.seclass.jobcompare6300.util;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import edu.gatech.seclass.jobcompare6300.data.model.Job;

public class JobComparator implements Comparator<Job> {

    private Map<String, Integer> weights= new HashMap<String, Integer>() {{
        put("salary", 1);
        put("bonus", 1);
        put("option", 1);
        put("hbp", 1);
        put("holiday", 1);
        put("stipend", 1);
    }};

    public JobComparator() {
    }

    public JobComparator(Map<String, Integer> weights) {
        this.weights = weights;
    }


    @Override
    public int compare(Job o1, Job o2) {
        return Float.compare(computeScore(o1), computeScore(o2));
    }

    public Map<String, Integer> getWeights() {
        return weights;
    }

    public void setWeights(Map<String, Integer> weights) {
        this.weights = weights;
    }

    private float computeScore(Job job){
        return job.getSalary() / job.getCost() * weights.get("salary") +
                job.getBonus() / job.getCost()  * weights.get("bonus") +
                job.getOption() / 3 * weights.get("option") +
                job.getHbp() * weights.get("hbp") +
                job.getHoliday() * job.getSalary() / 260 * weights.get("holiday") +
                job.getStipend() * 12 * weights.get("stipend");
    }
}
