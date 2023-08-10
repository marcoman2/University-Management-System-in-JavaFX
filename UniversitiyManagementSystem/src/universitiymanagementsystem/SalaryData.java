/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universitiymanagementsystem;

import java.sql.Date;

/**
 *
 * @author WINDOWS 10
 */
public class SalaryData {

    private Integer id;
    private String teacherID;
    private String name;
    private Double salaryPerDay;
    private Integer totalDays;
    private Double salaryPaid;
    private Date datePaid;

    public SalaryData(Integer id, String teacherID, String name, Double salaryPerDay,
            Integer totalDays, Double salaryPaid, Date datePaid) {
        this.id = id;
        this.teacherID = teacherID;
        this.name = name;
        this.salaryPerDay = salaryPerDay;
        this.totalDays = totalDays;
        this.salaryPaid = salaryPaid;
        this.datePaid = datePaid;
    }

    public Integer getId() {
        return id;
    }

    public String getTeacherID() {
        return teacherID;
    }

    public String getName() {
        return name;
    }

    public Double getSalaryPerDay() {
        return salaryPerDay;
    }

    public Integer getTotalDays() {
        return totalDays;
    }

    public Double getSalaryPaid() {
        return salaryPaid;
    }

    public Date getDatePaid() {
        return datePaid;
    }

}
