package uz.pdp.appcompany.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appcompany.entity.Address;
import uz.pdp.appcompany.payload.AddressDto;
import uz.pdp.appcompany.payload.Result;
import uz.pdp.appcompany.service.AddressService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/address")
public class AddresController {

    @Autowired
    AddressService addressService;

    /**
     * Get List Address
     * this is method returned addresslist;
     *
     * @return addressList
     */

    @GetMapping
    public ResponseEntity<List<Address>> getAddress() {
        List<Address> address = addressService.getAddress();
        return ResponseEntity.ok(address);
    }


    /**
     * Get by Id
     * This is method returned Address by Id;
     *
     * @param id
     * @return Address
     */

    @GetMapping("/{id}")
    public ResponseEntity<Address> getByAddress(@PathVariable Integer id) {
        Address address = addressService.getByAddress(id);
        return ResponseEntity.ok(address);
    }


    /**
     * This is method  for saved Address
     *
     * @param addressDto
     * @return Result
     */

    @PostMapping
    public ResponseEntity<Result> saveAddress(@Valid @RequestBody AddressDto addressDto) {
        Result result = addressService.saveAddress(addressDto);
        if (result.isSuccsess()) {
            return ResponseEntity.status(201).body(result);
        }
        return ResponseEntity.status(409).body(result);
    }


    /**
     * This is method editing address
     *
     * @param id
     * @param addressDto
     * @return Result
     */


    @PutMapping("/{id}")
    public ResponseEntity<Result> editAddres(@PathVariable Integer id, @Valid @RequestBody AddressDto addressDto) {
        Result result = addressService.editAddres(id, addressDto);
        if (result.isSuccsess()) {
            return ResponseEntity.status(202).body(result);

        }
        return ResponseEntity.status(409).body(result);
    }

    /**
     * This is method deleting address
     *
     * @param id
     * @return Result
     */

    @DeleteMapping("/{id}")
    public ResponseEntity<Result> deleteAddress(@PathVariable Integer id) {
        Result result = addressService.deleteAddress(id);
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
