package com.UniTimetableSysBackend.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseDto {
    private String courseName;
    private String courseCode;
    private String description;
    private String credits;
}
