package edu.max.bstore.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@Builder
public class Order {

    private Long id;
    private String userName;
    private String firstNameCustomer;
    private String lastNameCustomer;
    private String phoneNumber;
    private String bookName;
    private String created;
    private Integer status;
    private UUID bookId;
}
