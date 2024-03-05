package com.UniTimetableSysBackend.service;

import com.UniTimetableSysBackend.dto.request.CourseDto;
import com.UniTimetableSysBackend.model.Course;

import java.util.List;

public interface CourseService {

    Course createCourse(CourseDto courseDto);

    Course getCourseById(String courseId);

    List<Course> getAllCourses();

    Course updateCourse(String courseId, CourseDto courseDto);

    void deleteCourse(String courseId);
}
