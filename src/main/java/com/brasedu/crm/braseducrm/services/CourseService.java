package com.brasedu.crm.braseducrm.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.brasedu.crm.braseducrm.dto.response.ResponseCourseDTO;
import com.brasedu.crm.braseducrm.entities.CourseEntity;
import com.brasedu.crm.braseducrm.repositories.CourseRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;

    public CourseEntity include(CourseEntity course) {
        return courseRepository.save(course);
    }

    public CourseEntity edit(int id, CourseEntity course) {
        Optional<CourseEntity> existingCourse = courseRepository.findById(id);

        if (existingCourse.isPresent()) {
            CourseEntity updatedCourse = existingCourse.get();

            updatedCourse.setOportunities(course.getOportunities());
            updatedCourse.setName(course.getName());
            updatedCourse.setDescription(course.getDescription());
            updatedCourse.setValue(course.getValue());
            updatedCourse.setHourlyLoad(course.getHourlyLoad());

            return courseRepository.save(updatedCourse);
        } else {
            return null;
        }
    }

    public List<ResponseCourseDTO> listAll() {
        List<CourseEntity> courses = courseRepository.findAll();

        return courses.stream().map(ResponseCourseDTO::new).toList();
    }

    public CourseEntity findById(int id) {
        return courseRepository.findById(id).orElse(null);
    }

    public void delete(Integer id) {
        courseRepository.deleteById(id);
    }
}
