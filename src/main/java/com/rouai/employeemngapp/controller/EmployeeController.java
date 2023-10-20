package com.rouai.employeemngapp.controller;

import com.rouai.employeemngapp.dto.EmployeeDTO;
import com.rouai.employeemngapp.dto.UpdateEmployeeDTO;
import com.rouai.employeemngapp.exception.ResourceNotFoundException;
import com.rouai.employeemngapp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("/api")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    /**
     * Récupère un employé par son ID.
     *
     * @param employeeId L'ID de l'employé à récupérer.
     * @return Une réponse ResponseEntity contenant l'employé trouvé ou une erreur 404 si non trouvé.
     */
    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long employeeId) {
        EmployeeDTO employeeDTO = employeeService.getEmployeeById(employeeId);
        return ResponseEntity.status(HttpStatus.OK).body(employeeDTO);
    }

    /**
     * Gère les exceptions de type ResourceNotFoundException.
     *
     * @param ex L'exception ResourceNotFoundException.
     * @return Une réponse ResponseEntity avec un message d'erreur 404.
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }

    /**
     * Récupère tous les employés.
     *
     * @return Une réponse ResponseEntity contenant la liste de tous les employés.
     */
    @GetMapping("/employees")
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
        List<EmployeeDTO> employees = employeeService.getAllEmployees();
        return ResponseEntity.status(HttpStatus.OK).body(employees);
    }

    /**
     * Récupère un employé par son nom de famille.
     *
     * @param lastname Le nom de famille de l'employé à récupérer.
     * @return Une réponse ResponseEntity contenant l'employé trouvé ou une erreur 404 si non trouvé.
     */
    @GetMapping("employee/{lastname}")
    public ResponseEntity<EmployeeDTO> getEmployeeByLastname(@PathVariable String lastname) {
        EmployeeDTO employee = employeeService.getEmployeeByLastname(lastname);
        return ResponseEntity.status(HttpStatus.OK).body(employee);
    }
    /**
     * Vérifie si un employé existe en fonction de son ID.
     *
     * @param employeeId L'ID de l'employé à vérifier.
     * @return ResponseEntity avec un corps Boolean indiquant si l'employé existe (true) ou non (false).
     */
    @GetMapping("/employee/exists/{employeeId}")
    public ResponseEntity<Boolean> doesEmployeeExist(@PathVariable Long employeeId) {
        boolean exists = employeeService.doesEmployeeExist(employeeId);
        return ResponseEntity.status(HttpStatus.FOUND).body(exists);
    }
    /**
     * Récupère un employé en fonction de l'identifiant du département.
     *
     * @param departmentId L'identifiant du département pour lequel rechercher un employé.
     * @return ResponseEntity contenant un objet EmployeeDTO si l'employé est trouvé, sinon HttpStatus.NOT_FOUND.
     */
    @GetMapping("/byDepartment/{departmentId}")
    public ResponseEntity<EmployeeDTO> getEmployeeByDepartmentId(@PathVariable Long departmentId) {
        EmployeeDTO employeeDTO = employeeService.getEmployeeByDepartmentId(departmentId);
        return ResponseEntity.status(HttpStatus.OK).body(employeeDTO);
    }
    /**
     * Ajoute un nouvel employé à l'application.
     *
     * Cette méthode permet d'ajouter un nouvel employé en utilisant les données fournies dans le corps de la requête.
     *
     * @param employeeDTO Les informations de l'employé à ajouter.
     * @return ResponseEntity<EmployeeDTO> Un objet ResponseEntity contenant les informations de l'employé ajouté.
     *         Le statut de la réponse est HttpStatus.CREATED en cas de succès.
     */
    @PostMapping("/add")
    public ResponseEntity<EmployeeDTO> addEmployee(@RequestBody EmployeeDTO employeeDTO) {
        EmployeeDTO addedEmployee = employeeService.addEmployee(employeeDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedEmployee);
    }
    /**
     * Met à jour les informations d'un employé.
     *
     * @param employeeId       L'identifiant de l'employé à mettre à jour.
     * @param updateEmployeeDTO Les nouvelles informations de l'employé au format DTO.
     * @return ResponseEntity contenant un EmployeeDTO mis à jour et le statut HTTP OK (200).
     */
    @PutMapping("/{employeeId}/update")
    public ResponseEntity<EmployeeDTO> updateEmployee(
            @PathVariable Long employeeId,
            @RequestBody UpdateEmployeeDTO updateEmployeeDTO
    ) {
        EmployeeDTO updatedEmployee = employeeService.updateEmployee(employeeId, updateEmployeeDTO);
        return ResponseEntity.status(HttpStatus.OK).body(updatedEmployee);
    }
    /**
     * Supprime un employé à partir de son identifiant.
     *
     * Cette méthode permet de supprimer un employé de la base de données en utilisant son identifiant.
     *
     * @param employeeId L'identifiant de l'employé à supprimer.
     * @return ResponseEntity avec un message de confirmation indiquant que l'employé a été supprimé avec succès.
     */
    @DeleteMapping("/{employeeId}/delete")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long employeeId) {
        employeeService.deleteEmployee(employeeId);
        String confirmationMessage = "The employee has been successfully deleted.";
        return ResponseEntity.ok(confirmationMessage);
    }


}
