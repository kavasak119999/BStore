package edu.max.bstore.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class User {
    private String username;
    private String password;
    private String phoneNumber;
}
