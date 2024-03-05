package com.UniTimetableSysBackend.model;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "users")
@Getter
@Setter
public class User {
    @Id
    private String id;
    private String firstName;
    private String lastName;

    @Field("email")
    private String email;
    private String contactNo;
    private String password;
    private String role;
}
