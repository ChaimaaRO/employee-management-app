package com.rouai.employeemngapp.service;

import com.rouai.employeemngapp.model.Department;
import com.rouai.employeemngapp.model.Employee;
import com.rouai.employeemngapp.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Ce service gère les statistiques des employés.
 */
@Service
public class EmployeeStatisticsService {

    @Autowired
    private EmployeeRepository employeeRepository;

    /**
     * Récupère le nombre total d'employés.
     *
     * @return Le nombre total d'employés.
     */
    public int getTotalEmployees() {
        return employeeRepository.findAll().size();
    }

    /**
     * Récupère le nombre d'employés par département.
     *
     * @return Une carte (Map) associant le nom du département au nombre d'employés dans ce département.
     */
    public Map<String, Long> getEmployeesByDepartment() {
        List<Employee> employees = employeeRepository.findAll();
        Map<String, Long> departmentCount = employees.stream()
                .collect(Collectors.groupingBy(e -> e.getDepartment().getName(), Collectors.counting()));
        return departmentCount;
    }
}
