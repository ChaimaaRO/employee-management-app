package com.rouai.employeemngapp.dto;
public class DepartmentDTO {
    private Long departmentId;
    private String name;



    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DepartmentDTO() {
        // Constructeur par défaut
    }
}

