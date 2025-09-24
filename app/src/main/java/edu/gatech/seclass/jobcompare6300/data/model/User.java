package edu.gatech.seclass.jobcompare6300.data.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class User {

    private Job currentJob;
    private List<Job> jobs = new ArrayList<>();
    private Long userId;

    public User(long userId, List<Job> jobs, Job currentJob) {
        this.userId = userId;
        this.jobs = jobs;
        this.currentJob = currentJob;
    }

    public User(long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Job getCurrentJob() {
        return currentJob;
    }

    public void setCurrentJob(Job currentJob) {
        this.currentJob = currentJob;
    }

    public void updateCurrentJob(Job newCurrentJob) {
        if (currentJob != null) {
            // Update the existing current job
            jobs = jobs.stream()
                    .filter(job -> !Objects.equals(job.getId(), currentJob.getId()))
                    .collect(Collectors.toList());
        } else {
            // Ensure the new current job is not already in the list
            jobs = jobs.stream()
                    .filter(job -> !Objects.equals(job.getId(), newCurrentJob.getId()))
                    .collect(Collectors.toList());
        }
        this.currentJob = newCurrentJob;
        this.jobs.add(newCurrentJob);
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }

}
