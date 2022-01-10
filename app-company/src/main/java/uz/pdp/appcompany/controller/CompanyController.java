package uz.pdp.appcompany.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appcompany.entity.Company;
import uz.pdp.appcompany.payload.CompanyDto;
import uz.pdp.appcompany.payload.Result;
import uz.pdp.appcompany.service.CompanyService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/company")
public class CompanyController {

    @Autowired
    CompanyService companyService;

    /**
     * This is method retrun companylist
     *
     * @return companylist
     */

    @GetMapping
    public ResponseEntity<List<Company>> getCompany() {
        List<Company> company = companyService.getCompany();
        return ResponseEntity.ok(company);
    }

    /**
     * This is method returned Company by id
     *
     * @param id
     * @return Company
     */

    @GetMapping("/{id}")
    public ResponseEntity<Company> getByIdCompany(@PathVariable Integer id) {
        Company company = companyService.getByIdCompany(id);
        return ResponseEntity.ok(company);
    }

    /**
     * This is method saved Comany
     *
     * @param companyDto
     * @return Result
     */


    @PostMapping
    public ResponseEntity<Result> saveCompany(@Valid @RequestBody CompanyDto companyDto) {
        Result result = companyService.saveCompany(companyDto);
        if (result.isSuccsess()) {
            return ResponseEntity.status(201).body(result);
        }
        return ResponseEntity.status(409).body(result);
    }

    /**
     * This is method edting Company
     *
     * @param id
     * @param companyDto
     * @return
     */

    @PutMapping("/{id}")
    public ResponseEntity<Result> editCompany(@PathVariable Integer id, @Valid @RequestBody CompanyDto companyDto) {
        Result result = companyService.editCompany(id, companyDto);
        if (result.isSuccsess()) {
            return ResponseEntity.status(202).body(result);
        }
        return ResponseEntity.status(409).body(result);
    }

    /**
     * This is method deleted Company by id
     *
     * @param id
     * @return Result
     */

    @DeleteMapping("/{id}")
    public ResponseEntity<Result> deleteCompany(@PathVariable Integer id) {
        Result result = companyService.deleteCompany(id);
        if (result.isSuccsess()) {
            return ResponseEntity.status(200).body(result);
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

