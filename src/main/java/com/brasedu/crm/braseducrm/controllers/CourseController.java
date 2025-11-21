package com.brasedu.crm.braseducrm.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brasedu.crm.braseducrm.entities.CourseEntity;
import com.brasedu.crm.braseducrm.services.CourseService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/course")
public class CourseController {
    private final CourseService courseService;

    @GetMapping
    public ResponseEntity<List<CourseEntity>> listAll() {
        List<CourseEntity> list = courseService.listAll();
        return ResponseEntity.ok().body(list);
    }

    @PostMapping
    public ResponseEntity<CourseEntity> include(@RequestBody CourseEntity course) {
        CourseEntity newCourse = courseService.include(course);

        if (newCourse != null) {
            return new ResponseEntity<>(newCourse, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/id")
    public ResponseEntity<CourseEntity> edit(@PathVariable int id, @RequestBody CourseEntity course) {
        CourseEntity updated = courseService.edit(id, course);

        if (updated != null) {
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/id")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        courseService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
