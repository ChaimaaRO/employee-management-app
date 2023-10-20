package com.rouai.employeemngapp.repository;


import com.rouai.employeemngapp.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Employee findByLastname(String lastname);

    Employee findByDepartmentId(Long id);
}
