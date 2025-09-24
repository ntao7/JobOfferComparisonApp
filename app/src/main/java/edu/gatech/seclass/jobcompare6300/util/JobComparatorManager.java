package edu.gatech.seclass.jobcompare6300.util;

public class JobComparatorManager {
    private static JobComparatorManager instance;
    private JobComparator jobComparator;

    private JobComparatorManager() {}

    public static synchronized JobComparatorManager getInstance() {
        if (instance == null) {
            instance = new JobComparatorManager();
        }
        return instance;
    }

    public JobComparator getJobComparator() {
        return jobComparator;
    }

    public void setJobComparator(JobComparator jobComparator) {
        this.jobComparator = jobComparator;
    }
}
