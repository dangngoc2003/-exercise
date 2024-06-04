package com.example.test.model.command;

import com.example.test.domain.Customer;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class CustomerCommand {
    private Long id;
    @NotBlank
    @Schema(description = "Têm của khách")
    private String name;
    @Schema(description = "tuổi của khách")
    private String age;
    @Schema(description = "email của khách")
    private String email;
    @Schema(description = "số điện thoại của khách")
    private String phone;

    public static Customer entityToModel(CustomerCommand customer) {
        return Customer.builder()
                .name(customer.getName())
                .age(customer.getAge())
                .age(customer.getAge())
                .phone(customer.getPhone())
                .build();
    }
    public static Customer dtoToEntity(CustomerCommand command){
        Customer customer = new Customer();
        customer.getId();
        customer.setName(command.getName());
        customer.setAge(command.getAge());
        customer.setEmail(command.getEmail());
        customer.setPhone(command.getPhone());
        return customer;
    }



}
