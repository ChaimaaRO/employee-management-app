package com.rouai.employeemngapp.service;

import com.rouai.employeemngapp.dto.EmployeeDTO;
import com.rouai.employeemngapp.dto.UpdateEmployeeDTO;
import com.rouai.employeemngapp.exception.ResourceNotFoundException;
import com.rouai.employeemngapp.model.Employee;
import com.rouai.employeemngapp.repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service responsable de la gestion des employés.
 */
@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Récupère un employé par son ID.
     *
     * @param employeeId L'ID de l'employé à récupérer.
     * @return L'employé correspondant sous forme d'EmployeeDTO.
     * @throws ResourceNotFoundException Si aucun employé n'est trouvé avec l'ID donné.
     */
    public EmployeeDTO getEmployeeById(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + employeeId));

        return modelMapper.map(employee, EmployeeDTO.class);
    }

    /**
     * Récupère tous les employés.
     *
     * @return La liste de tous les employés sous forme de liste d'EmployeeDTO.
     */
    public List<EmployeeDTO> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        // Utilisez ModelMapper pour mapper Employee vers EmployeeDTO
        return employees.stream()
                .map(employee -> modelMapper.map(employee, EmployeeDTO.class))
                .collect(Collectors.toList());
    }

    /**
     * Récupère un employé par son nom de famille.
     *
     * @param lastname Le nom de famille de l'employé à récupérer.
     * @return L'employé correspondant sous forme d'EmployeeDTO.
     * @throws ResourceNotFoundException Si aucun employé n'est trouvé avec le nom de famille donné.
     */
    public EmployeeDTO getEmployeeByLastname(String lastname) {
        Employee employee = employeeRepository.findByLastname(lastname);
        if (employee != null) {
            return modelMapper.map(employee, EmployeeDTO.class);
        } else {
            throw new ResourceNotFoundException("Employee not found with lastname: " + lastname);
        }
    }
    /**
     * Vérifie si un employé existe dans la base de données par son ID.
     *
     * @param employeeId L'ID de l'employé à vérifier.
     * @return true si l'employé existe, sinon false.
     */
    public boolean doesEmployeeExist(Long employeeId) {
        return employeeRepository.existsById(employeeId);
    }

    /**
     * Récupère un employé en fonction de l'identifiant du département.
     *
     * @param departmentId L'identifiant du département pour lequel rechercher un employé.
     * @return EmployeeDTO contenant les informations de l'employé trouvé.
     * @throws ResourceNotFoundException Si aucun employé n'est trouvé pour le département spécifié.
     */
    public EmployeeDTO getEmployeeByDepartmentId(Long departmentId) {
        Employee employee = employeeRepository.findByDepartmentId(departmentId);
        if (employee == null) {
            throw new ResourceNotFoundException("Aucun employé trouvé pour ce département.");
        }

        return modelMapper.map(employee, EmployeeDTO.class);
    }
    /**
     * Ajoute un nouvel employé à l'application.
     *
     * Cette méthode prend un objet EmployeeDTO représentant les informations de l'employé en entrée.
     * Elle utilise un ModelMapper pour mapper l'objet EmployeeDTO vers un objet Employee,
     * puis enregistre l'employé dans le référentiel (employeeRepository).
     * Enfin, elle retourne un objet EmployeeDTO représentant l'employé ajouté.
     *
     * @param employeeDTO Les informations de l'employé à ajouter.
     * @return Un objet EmployeeDTO représentant l'employé ajouté.
     */
    public EmployeeDTO addEmployee(EmployeeDTO employeeDTO) {
        Employee employee = modelMapper.map(employeeDTO, Employee.class);
        Employee savedEmployee = employeeRepository.save(employee);
        return modelMapper.map(savedEmployee, EmployeeDTO.class);
    }
    /**
     * Met à jour les informations d'un employé existant en fonction de l'ID de l'employé.
     *
     * @param employeeId         L'identifiant unique de l'employé à mettre à jour.
     * @param updateEmployeeDTO  Un objet contenant les nouvelles informations de l'employé.
     * @return                   Un objet EmployeeDTO représentant l'employé mis à jour.
     * @throws ResourceNotFoundException Si l'employé avec l'ID spécifié n'est pas trouvé.
     */

    public EmployeeDTO updateEmployee(Long employeeId, UpdateEmployeeDTO updateEmployeeDTO) {
        Employee existingEmployee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

        // Mettez à jour les champs autorisés
        if (updateEmployeeDTO.getFirstname() != null) {
            existingEmployee.setFirstname(updateEmployeeDTO.getFirstname());
        }
        if (updateEmployeeDTO.getLastname() != null) {
            existingEmployee.setLastname(updateEmployeeDTO.getLastname());
        }
        if (updateEmployeeDTO.getEmail() != null) {
            existingEmployee.setEmail(updateEmployeeDTO.getEmail());
        }
        if (updateEmployeeDTO.getPhonenumber() != null) {
            existingEmployee.setPhonenumber(updateEmployeeDTO.getPhonenumber());
        }
        if (updateEmployeeDTO.getJobtitle() != null) {
            existingEmployee.setJobtitle(updateEmployeeDTO.getJobtitle());
        }
        if (updateEmployeeDTO.getDateofjoining() != null) {
            existingEmployee.setDateofjoining(updateEmployeeDTO.getDateofjoining());
        }


        Employee updatedEmployee = employeeRepository.save(existingEmployee);
        return modelMapper.map(updatedEmployee, EmployeeDTO.class);
    }

    /**
     * Supprime un employé à partir de son identifiant.
     *
     * Cette méthode permet de rechercher un employé par son identifiant, puis de le supprimer s'il existe.
     *
     * @param employeeId L'identifiant de l'employé à supprimer.
     * @throws ResourceNotFoundException Si l'employé n'est pas trouvé dans la base de données.
     */
    public void deleteEmployee(Long employeeId) {
        Employee existingEmployee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

        employeeRepository.delete(existingEmployee);

    }

}
