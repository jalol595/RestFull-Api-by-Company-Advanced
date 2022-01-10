package uz.pdp.appcompany.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appcompany.entity.Department;
import uz.pdp.appcompany.payload.DepartmentDto;
import uz.pdp.appcompany.payload.Result;
import uz.pdp.appcompany.service.DepartmentService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/department")
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

    /**
     * This is method returned ListDepartment
     *
     * @return ListDepartment
     */

    @GetMapping
    public ResponseEntity<List<Department>> getDepartment() {
        List<Department> alldepartment = departmentService.getDepartment();
        return ResponseEntity.ok(alldepartment);
    }

    /**
     * This is method returned Department By Id
     *
     * @param id
     * @return Department By Id
     */

    @GetMapping("/{id}")
    public Department getByIdDepartment(@PathVariable Integer id) {
        Department department = departmentService.getByIdDepartment(id);
        return department;
    }

    /**
     * This is method saved Department
     *
     * @param departmentDto
     * @return Result
     */

    @PostMapping
    public ResponseEntity<Result> saveDepartment(@Valid @RequestBody DepartmentDto departmentDto) {
        Result result = departmentService.saveDepartment(departmentDto);
        if (result.isSuccsess()) {
            return ResponseEntity.status(201).body(result);
        }
        return ResponseEntity.status(409).body(result);
    }

    /**
     * This is method edited Department
     *
     * @param id
     * @param departmentDto
     * @return
     */

    @PutMapping("/{id}")
    public ResponseEntity<Result> editDepartment(@PathVariable Integer id, @Valid @RequestBody DepartmentDto departmentDto) {
        Result result = departmentService.editDepartment(id, departmentDto);
        if (result.isSuccsess()) {
            return ResponseEntity.status(202).body(result);
        }
        return ResponseEntity.status(409).body(result);
    }

    /**
     * This is method deleted Department
     *
     * @param id
     * @return
     */

    @DeleteMapping("/{id}")
    public ResponseEntity<Result> deleteDepartment(@PathVariable Integer id) {
        Result result = departmentService.deleteDepartment(id);
        if (result.isSuccsess()) {
            return ResponseEntity.status(204).body(result);
        }
        return ResponseEntity.status(409).body(result);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
