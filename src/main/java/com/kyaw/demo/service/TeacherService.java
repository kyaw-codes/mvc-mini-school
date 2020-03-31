package com.kyaw.demo.service;

import com.kyaw.demo.domain.Teacher;

import java.util.List;

public interface TeacherService {

    List<Teacher> listTeachers();

    void save(Teacher teacher);

    Teacher findById(int id);

    void update(int id, Teacher teacher);

    void delete(int id);
}
