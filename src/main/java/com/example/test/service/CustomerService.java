package com.example.test.service;

import com.example.test.common.CommonException;
import com.example.test.domain.Customer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.example.test.model.command.CustomerCommand;
import com.example.test.model.criteria.CustomerCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.example.test.problem.BadRequestException;
import reactor.core.publisher.Mono;
import com.example.test.repository.CustomerRepository;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    public Mono<List<Customer>> findAll() {
        return Mono.just((List<Customer>) customerRepository.findAll());
    }

    public Mono<Customer> findId(CustomerCriteria criteria) {
        log.info("id by customerCriteria", criteria.getId());
        Long id = criteria.getId();
        return customerRepository.findById(id)
                .map(Customer::entityToModel)
                .switchIfEmpty(Mono.error(new CommonException("list_customer_empty", "list customer empty", HttpStatus.NOT_FOUND)));
    }

    public Mono<Customer> update(CustomerCriteria criteria, CustomerCommand customerCommand) {
        if (criteria.getId() == null) {
            return Mono.error(BadRequestException.of("id_is_empty", "id is empty"));
        }
        return customerRepository.findById(criteria.getId())
                .flatMap(customer -> {
                    customer.setName(customerCommand.getName());
                    customer.setAge(customerCommand.getAge());
                    customer.setEmail(customerCommand.getEmail());
                    customer.setPhone(customerCommand.getPhone());
                    return customerRepository.save(customer);
                });
    }

    public Mono<Customer> createNewCustomer(CustomerCommand customerCommand) {
        log.info("customerCommand", customerCommand);
        return Mono.just(customerCommand).
                map(CustomerCommand::dtoToEntity)
                .flatMap(customer -> customerRepository.save(customer))
                .switchIfEmpty(Mono.error(new CommonException("customer_empty", " customer empty", HttpStatus.NOT_FOUND)));
    }

    public void delete(CustomerCriteria criteria) {
        customerRepository.deleteById(criteria.getId());
    }
}
