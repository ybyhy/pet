package cn.edu.cdu.yhy.bean;

import java.util.Date;

public class Income {
    private Integer id;

    private Date date;

    private Float income;

    private String name;

    public Income(Integer id, Date date, Float income, String name) {
        this.id = id;
        this.date = date;
        this.income = income;
        this.name = name;
    }

    public Income() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Float getIncome() {
        return income;
    }

    public void setIncome(Float income) {
        this.income = income;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}