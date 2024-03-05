package com.UniTimetableSysBackend.controller;


import com.UniTimetableSysBackend.dto.request.CourseDto;
import com.UniTimetableSysBackend.dto.response.CustomResponse;
import com.UniTimetableSysBackend.model.Course;
import com.UniTimetableSysBackend.security.JwtTokenProvider;
import com.UniTimetableSysBackend.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("/createCourse")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CustomResponse<Course>> createCourse(@RequestBody CourseDto courseDto, @RequestHeader("Authorization") String token) {
        String userId = jwtTokenProvider.getEmailFromToken(token.substring(7));
        String role = jwtTokenProvider.getRoleFromToken(token.substring(7));

        if (!role.equals("ADMIN")) {
            CustomResponse<Course> response = new CustomResponse<>(null, HttpStatus.FORBIDDEN.value(), "User does not have permission to create a course", false);
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
        }

        // Create the course
        Course createdCourse = courseService.createCourse(courseDto);
        CustomResponse<Course> response = new CustomResponse<>(createdCourse, HttpStatus.CREATED.value(), "Course created successfully", true);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/getCourse")
    public ResponseEntity<CustomResponse<Course>> getCourseById(@RequestParam String courseId, @RequestHeader("Authorization") String token) {
        String userId = jwtTokenProvider.getEmailFromToken(token.substring(7));
        String role = jwtTokenProvider.getRoleFromToken(token.substring(7));

        // Check if the user has permission to get course details
        if (!role.equals("ADMIN") && !role.equals("FACULTY") && !role.equals("STUDENT")) {
            CustomResponse<Course> response = new CustomResponse<>(null, HttpStatus.FORBIDDEN.value(), "User does not have permission to get course details", false);
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
        }

        // Get the course details
        Course course = courseService.getCourseById(courseId);
        if (course == null) {
            CustomResponse<Course> response = new CustomResponse<>(null, HttpStatus.NOT_FOUND.value(), "Course not found", false);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        CustomResponse<Course> response = new CustomResponse<>(course, HttpStatus.OK.value(), "Course details retrieved successfully", true);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    // Update an existing course
    @PutMapping("/updateCourse/{courseId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CustomResponse<Course>> updateCourse(@PathVariable String courseId, @RequestBody CourseDto courseDto, @RequestHeader("Authorization") String token) {
        String userId = jwtTokenProvider.getEmailFromToken(token.substring(7));
        String role = jwtTokenProvider.getRoleFromToken(token.substring(7));

        // Check if the user has permission to update courses
        if (!role.equals("ADMIN")) {
            CustomResponse<Course> response = new CustomResponse<>(null, HttpStatus.FORBIDDEN.value(), "User does not have permission to update courses", false);
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
        }

        // Update the course
        Course updatedCourse = courseService.updateCourse(courseId, courseDto);
        if (updatedCourse == null) {
            CustomResponse<Course> response = new CustomResponse<>(null, HttpStatus.NOT_FOUND.value(), "Course not found", false);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        CustomResponse<Course> response = new CustomResponse<>(updatedCourse, HttpStatus.OK.value(), "Course updated successfully", true);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    @GetMapping("/getAllCourses")
    @PreAuthorize("hasAnyRole('ADMIN', 'FACULTY', 'STUDENT')")
    public ResponseEntity<CustomResponse<List<Course>>> getAllCourses(@RequestHeader("Authorization") String token) {
        String userId = jwtTokenProvider.getEmailFromToken(token.substring(7));
        String role = jwtTokenProvider.getRoleFromToken(token.substring(7));


        if (!role.equals("ADMIN") && !role.equals("FACULTY") && !role.equals("STUDENT")) {
            CustomResponse<List<Course>> response = new CustomResponse<>(null, HttpStatus.FORBIDDEN.value(), "User does not have permission to get all courses", false);
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
        }

        List<Course> courses = courseService.getAllCourses();
        CustomResponse<List<Course>> response = new CustomResponse<>(courses, HttpStatus.OK.value(), "Courses retrieved successfully", true);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    @DeleteMapping("/deleteCourse/{courseId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CustomResponse<Void>> deleteCourse(@PathVariable String courseId, @RequestHeader("Authorization") String token) {
        String userId = jwtTokenProvider.getEmailFromToken(token.substring(7));
        String role = jwtTokenProvider.getRoleFromToken(token.substring(7));


        if (!role.equals("ADMIN")) {
            CustomResponse<Void> response = new CustomResponse<>(null, HttpStatus.FORBIDDEN.value(), "User does not have permission to delete courses", false);
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
        }

        courseService.deleteCourse(courseId);
        CustomResponse<Void> response = new CustomResponse<>(null, HttpStatus.OK.value(), "Course deleted successfully", true);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
