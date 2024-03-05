package com.UniTimetableSysBackend.repository;

import com.UniTimetableSysBackend.model.Course;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CourseRepository extends MongoRepository<Course, String> {


}
