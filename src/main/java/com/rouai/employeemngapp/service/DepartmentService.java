package com.rouai.employeemngapp.service;

import com.rouai.employeemngapp.dto.DepartmentDTO;
import com.rouai.employeemngapp.model.Department;
import com.rouai.employeemngapp.repository.DepartmentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service responsable de la gestion des départements.
 */
@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private ModelMapper modelMapper;

    /**
     * Récupère la liste de tous les départements sous forme de DTO.
     *
     * @return Une liste de DepartmentDTO contenant les informations des départements.
     */
    public List<DepartmentDTO> getAllDepartments() {
        // Récupérer la liste des départements depuis le repository
        List<Department> departments = departmentRepository.findAll();

        // Mapper les entités Department en DTO DepartmentDTO
        return departments.stream()
                .map(department -> modelMapper.map(department, DepartmentDTO.class))
                .collect(Collectors.toList());
    }
}
