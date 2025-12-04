package com.brasedu.crm.braseducrm.dto.response;

import com.brasedu.crm.braseducrm.entities.CourseEntity;

import lombok.Data;

@Data
public class ResponseCourseDTO {
    int id;
    String name;
    String description;
    float value;
    int hourlyLoad;

    public ResponseCourseDTO(CourseEntity entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.description = entity.getDescription();
        this.value = entity.getValue();
        this.hourlyLoad = entity.getHourlyLoad();
    }
}
