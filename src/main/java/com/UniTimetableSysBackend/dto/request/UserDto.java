package com.UniTimetableSysBackend.dto.request;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto{
    private String firstName;
    private String lastName;
    private String email;
    private String contactNo;
    private String password;
    private String role;
}
