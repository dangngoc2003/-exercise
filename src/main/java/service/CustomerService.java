package service;

import common.CommonException;
import domain.Customer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import model.command.CustomerCommand;
import model.criteria.CustomerCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import problem.BadRequestException;
import reactor.core.publisher.Mono;
import repository.CustomerRepository;

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

    public Mono<Customer> createNewCustomer(CustomerCommand customerCommand) {
        log.info("customerCommand", customerCommand);
        return Mono.just(customerCommand).
                map(CustomerCommand::entityToModel)
                .flatMap(customer -> customerRepository.save(customer))
                .switchIfEmpty(Mono.error(new CommonException("customer_empty", " customer empty", HttpStatus.NOT_FOUND)));
    }

    public void delete(CustomerCriteria criteria) {
        customerRepository.deleteById(criteria.getId());
    }
}
