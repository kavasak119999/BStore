package edu.max.bstore.dto;

import edu.max.bstore.enumeration.Status;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class AdminUser {
    private String userName;
    private String password;
    private String phoneNumber;
    private Status status;
}
