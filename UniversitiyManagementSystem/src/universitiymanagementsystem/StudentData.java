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
public class StudentData {

    private Integer id;
    private String studentID;
    private String fullName;
    private String gender;
    private Date birthDate;
    private String year;
    private String course;
    private String section;
    private Double payment;
    private String statusPayment;
    private String image;
    private Date dateInsert;
    private Date dateUpdate;
    private Date dateDelete;
    private String status;
    private String semester;

    public StudentData(Integer id, String studentID, String fullName, String gender,
            Date birthDate, String year, String course, String section,
            Double payment, String statusPayment, String image, Date dateInsert,
            Date dateUpdate, Date dateDelete, String status) {
        this.id = id;
        this.studentID = studentID;
        this.fullName = fullName;
        this.gender = gender;
        this.birthDate = birthDate;
        this.year = year;
        this.course = course;
        this.section = section;
        this.payment = payment;
        this.statusPayment = statusPayment;
        this.image = image;
        this.dateInsert = dateInsert;
        this.dateUpdate = dateUpdate;
        this.dateDelete = dateDelete;
        this.status = status;
    }

    public StudentData(Integer id, String studentID, String fullName, String year, String course,
            String section, String semester, Double payment, String statusPayment, Date dateInsert, String status) {
        this.id = id;
        this.studentID = studentID;
        this.fullName = fullName;
        this.year = year;
        this.course = course;
        this.section = section;
        this.semester = semester;
        this.payment = payment;
        this.statusPayment = statusPayment;
        this.dateInsert = dateInsert;
        this.status = status;
    }

    public StudentData(Integer id, String studentID, String fullName, String year, String course,
            String section, String semester, Double payment, String statusPayment, String image,
            Date dateUpdate, String status) {
        this.id = id;
        this.studentID = studentID;
        this.fullName = fullName;
        this.year = year;
        this.course = course;
        this.section = section;
        this.semester = semester;
        this.payment = payment;
        this.statusPayment = statusPayment;
        this.image = image;
        this.dateUpdate = dateUpdate;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public String getStudentID() {
        return studentID;
    }

    public String getFullName() {
        return fullName;
    }

    public String getGender() {
        return gender;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public String getYear() {
        return year;
    }

    public String getCourse() {
        return course;
    }

    public String getSemester() {
        return semester;
    }

    public String getSection() {
        return section;
    }

    public Double getPayment() {
        return payment;
    }

    public String getStatusPayment() {
        return statusPayment;
    }

    public String getImage() {
        return image;
    }

    public Date getDateInsert() {
        return dateInsert;
    }

    public Date getDateUpdate() {
        return dateUpdate;
    }

    public Date getDateDelete() {
        return dateDelete;
    }

    public String getStatus() {
        return status;
    }
}
