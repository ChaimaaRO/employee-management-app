package com.rouai.employeemngapp.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Cette classe représente un département dans l'entreprise.
 */
@Entity
@Table(name = "department")
public class Department {

    /**
     * L'identifiant unique du département.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "department_id")
//    private Long departmentId;
    private Long id;
    /**
     * Le nom du département.
     */
    @Column(name = "name")
    private String name;

    /**
     * La liste des employés affiliés à ce département.
     */
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private List<Employee> employees = new ArrayList<>();

    /**
     * Obtient l'identifiant unique du département.
     * @return L'identifiant unique du département.
     */
    public Long getDepartmentId() {

        return id;
    }

    /**
     * Définit l'identifiant unique du département.
     * @param departmentId L'identifiant unique du département.
     */
    public void setDepartmentId(Long departmentId) {
        this.id = departmentId;
    }

    /**
     * Obtient le nom du département.
     * @return Le nom du département.
     */
    public String getName() {
        return name;
    }

    /**
     * Définit le nom du département.
     * @param name Le nom du département.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Obtient la liste des employés affiliés à ce département.
     * @return La liste des employés affiliés à ce département.
     */
    public List<Employee> getEmployees() {
        return employees;
    }

    /**
     * Définit la liste des employés affiliés à ce département.
     * @param employees La liste des employés affiliés à ce département.
     */
    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
