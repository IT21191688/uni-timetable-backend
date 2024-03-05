package com.UniTimetableSysBackend.model;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "courses")
@Getter
@Setter
public class Course {


    @Id
    private String courseId;
    private String courseName;
    private String courseCode;
    private String description;
    private String credits;

}
