package com.example.test.endpoint;

import com.example.test.domain.Customer;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import com.example.test.model.command.CustomerCommand;
import com.example.test.model.criteria.CustomerCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import com.example.test.service.CustomerService;

import java.util.List;

@RestController
@RequestMapping("customers")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @GetMapping
    @Operation(summary = "list customer")
    public ResponseEntity<Mono<List<Customer>>> getAllPayment() {
        return ResponseEntity.ok(customerService.findAll());
    }

    @GetMapping()
    @Operation(summary = "find customer by id")
    public ResponseEntity<Mono<Customer>> findById(@Valid CustomerCriteria criteria){
        return ResponseEntity.ok(customerService.findId(criteria));
    }
    @PostMapping()
    @Operation(summary = "create customer")
    public Mono<Customer> createCustomer(@Valid @RequestBody CustomerCommand customerCommand){
        return customerService.createNewCustomer(customerCommand);
    }
    @DeleteMapping("/{id}")
    @Operation(summary = "delete customer by id")
    public void  delete(@Valid CustomerCriteria criteria){
        customerService.delete(criteria);
    }
}
