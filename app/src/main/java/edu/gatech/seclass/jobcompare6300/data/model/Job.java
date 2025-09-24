package edu.gatech.seclass.jobcompare6300.data.model;

import java.io.Serializable;

public class Job implements Serializable {
    private Long id;

    private Long userId;

    private String title;
    private String company;
    private String city;
    private String state;
    private Integer cost;
    private Float salary;
    private Float bonus;
    private Float option;
    private Float hbp;
    private Integer holiday;
    private Float stipend;

    public Job() {
    }


    @Override
    public String toString() {
        return "Job{" +
                "title='" + title + '\'' +
                ", company='" + company + '\'' +
                '}';
    }

    public Job(String title, String company, float salary, float bonus, int cost) {
        this.title = title;
        this.company = company;
        this.salary = salary;
        this.bonus = bonus;
        this.cost = cost;
    }


    public Job(long userId, String title, String company, String city, String state, Integer cost, Float salary, Float bonus, Float option, Float hbp, Integer holiday, Float stipend) {
        this.userId = userId;
        this.title = title;
        this.company = company;
        this.city = city;
        this.state = state;
        this.cost = cost;
        this.salary = salary;
        this.bonus = bonus;
        this.option = option;
        this.hbp = hbp;
        this.holiday = holiday;
        this.stipend = stipend;
    }

    public Long getId(){
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public Float getSalary() {
        return salary;
    }

    public void setSalary(Float salary) {
        this.salary = salary;
    }

    public Float getBonus() {
        return bonus;
    }

    public void setBonus(Float bonus) {
        this.bonus = bonus;
    }

    public Float getOption() {
        return option;
    }

    public void setOption(Float option) {
        this.option = option;
    }

    public Float getHbp() {
        return hbp;
    }

    public void setHbp(Float hbp) {
        this.hbp = hbp;
    }

    public Integer getHoliday() {
        return holiday;
    }

    public void setHoliday(Integer holiday) {
        this.holiday = holiday;
    }

    public Float getStipend() {
        return stipend;
    }

    public void setStipend(Float stipend) {
        this.stipend = stipend;
    }
}
