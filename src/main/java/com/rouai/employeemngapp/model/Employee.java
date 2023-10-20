package com.rouai.employeemngapp.model;

import jakarta.persistence.*;

import java.util.Date;

/**
 * Cette classe représente un employé dans le système.
 */
@Entity
@Table(name = "employees")
public class Employee {
    /**
     * L'identifiant unique de l'employé.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Long employeeId;

    /**
     * Le prénom de l'employé.
     */
    @Column(name = "firstname")
    private String firstname;

    /**
     * Le nom de famille de l'employé.
     */
    @Column(name = "lastname")
    private String lastname;

    /**
     * L'adresse e-mail de l'employé.
     */
    @Column(name = "email")
    private String email;

    /**
     * Le numéro de téléphone de l'employé.
     */
    @Column(name = "phonenumber")
    private String phonenumber;

    /**
     * Le titre de poste de l'employé.
     */
    @Column(name = "jobtitle")
    private String jobtitle;

    /**
     * La date à laquelle l'employé a rejoint l'entreprise.
     */
    @Column(name = "dateofjoining")
    private Date dateofjoining;

    /**
     * La relation Many-to-One avec le département auquel l'employé appartient.
     */
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;



    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getJobtitle() {
        return jobtitle;
    }

    public void setJobtitle(String jobtitle) {
        this.jobtitle = jobtitle;
    }

    public Date getDateofjoining() {
        return dateofjoining;
    }

    public void setDateofjoining(Date dateofjoining) {
        this.dateofjoining = dateofjoining;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}

