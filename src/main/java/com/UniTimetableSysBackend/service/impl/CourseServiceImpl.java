package com.UniTimetableSysBackend.service.impl;

import com.UniTimetableSysBackend.dto.request.CourseDto;
import com.UniTimetableSysBackend.model.Course;
import com.UniTimetableSysBackend.repository.CourseRepository;
import com.UniTimetableSysBackend.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {


    @Autowired
    private CourseRepository courseRepository;
    @Override
    public Course createCourse(CourseDto courseDto) {

        Course course = new Course();
        course.setCourseName(courseDto.getCourseName());
        course.setCourseCode(courseDto.getCourseCode());
        course.setDescription(courseDto.getDescription());
        course.setCredits(courseDto.getCredits());

        // Save the Course entity to the repository
        return courseRepository.save(course);
    }

    @Override
    public Course getCourseById(String courseId) {
        Optional<Course> optionalCourse = courseRepository.findById(courseId);
        return optionalCourse.orElse(null);
    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Course updateCourse(String courseId, CourseDto courseDto) {
        if (courseRepository.existsById(courseId)) {


            Course existingCourse = courseRepository.findById(courseId).orElse(null);
            if (existingCourse != null) {
                // Update the existing course with values from the DTO
                existingCourse.setCourseName(courseDto.getCourseName());
                existingCourse.setCourseCode(courseDto.getCourseCode());
                existingCourse.setDescription(courseDto.getDescription());
                existingCourse.setCredits(courseDto.getCredits());

                // Save the updated course back to the repository
                return courseRepository.save(existingCourse);
            }
        }
        return null; // or throw an exception
    }

    @Override
    public void deleteCourse(String courseId) {

        courseRepository.deleteById(courseId);

    }
}
