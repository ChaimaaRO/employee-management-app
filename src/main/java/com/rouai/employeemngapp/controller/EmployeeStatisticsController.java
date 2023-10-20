package com.rouai.employeemngapp.controller;

import com.rouai.employeemngapp.service.EmployeeStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Contrôleur pour gérer les statistiques des employés.
 */
@RestController
@RequestMapping("/api/employee-statistics")
public class EmployeeStatisticsController {

    @Autowired
    private EmployeeStatisticsService employeeStatisticsService;

    /**
     * Endpoint pour obtenir le nombre total d'employés.
     *
     * @return Réponse contenant le nombre total d'employés.
     */
    @GetMapping("/total-employees")
    public ResponseEntity<Integer> getTotalEmployees() {
        int totalEmployees = employeeStatisticsService.getTotalEmployees();
        return ResponseEntity.ok(totalEmployees);
    }

    /**
     * Endpoint pour obtenir le nombre d'employés par département.
     *
     * @return Réponse contenant une carte (Map) des employés par département.
     */
    @GetMapping("/employees-by-department")
    public ResponseEntity<Map<String, Long>> getEmployeesByDepartment() {
        Map<String, Long> employeesByDepartment = employeeStatisticsService.getEmployeesByDepartment();
        return ResponseEntity.ok(employeesByDepartment);
    }
}

