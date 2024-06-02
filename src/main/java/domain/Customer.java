package domain;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import model.command.CustomerCommand;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = false)
@Data
@Builder
@NoArgsConstructor
@Table("an_customer")
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Customer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String name;
    private String age;
    private String email;
    private String phone;
    public static Customer entityToModel(Customer customer) {
        return Customer.builder()
                .name(customer.getName())
                .age(customer.getAge())
                .age(customer.getAge())
                .phone(customer.getPhone())
                .build();
    }


}
