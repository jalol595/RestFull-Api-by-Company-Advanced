package uz.pdp.appcompany.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appcompany.entity.Worker;
import uz.pdp.appcompany.payload.Result;
import uz.pdp.appcompany.payload.WorkerDto;
import uz.pdp.appcompany.repository.AddressRepository;
import uz.pdp.appcompany.repository.DepartmentRepository;
import uz.pdp.appcompany.service.WorkerService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/worker")
public class WorkerController {

    @Autowired
    WorkerService workerService;


    /**
     * This is method returned List worker
     *
     * @return
     */

    @GetMapping
    public ResponseEntity<List<Worker>> getWorker() {
        List<Worker> workers = workerService.getWorker();
        return ResponseEntity.ok(workers);
    }

    /**
     * This is method returned Worker by id
     *
     * @param id
     * @return
     */

    @GetMapping("/{id}")
    public ResponseEntity<Worker> getByIdWorker(@PathVariable Integer id) {
        Worker byIdWorker = workerService.getByIdWorker(id);
        return ResponseEntity.ok(byIdWorker);
    }

    /**
     * This is method saved Worker
     *
     * @param workerDto
     * @return
     */

    @PostMapping
    public ResponseEntity<Result> saveWorker(@Valid @RequestBody WorkerDto workerDto) {
        Result result = workerService.saveWorker(workerDto);
        if (result.isSuccsess()) {
            return ResponseEntity.status(200).body(result);
        }
        return ResponseEntity.status(409).body(result);
    }

    /**
     * This is method edited Worker
     *
     * @param id
     * @param workerDto
     * @return
     */

    @PutMapping("/{id}")
    public ResponseEntity<Result> editWorker(@PathVariable Integer id, @Valid @RequestBody WorkerDto workerDto) {
        Result result = workerService.editWorker(id, workerDto);
        if (result.isSuccsess()) {
            return ResponseEntity.status(202).body(result);
        }
        return ResponseEntity.status(409).body(result);
    }

    /**
     * This is method edited Worker
     *
     * @param id
     * @return
     */


    @DeleteMapping("/{id}")
    public ResponseEntity<Result> deletedWorker(@PathVariable Integer id) {
        Result result = workerService.deletedWorker(id);
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
