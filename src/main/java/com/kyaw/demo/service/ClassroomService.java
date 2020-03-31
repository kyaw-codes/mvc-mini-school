package com.kyaw.demo.service;

import com.kyaw.demo.domain.Classroom;

import java.util.List;

public interface ClassroomService {

    void register(Classroom classroom);

    List<Classroom> listClassroom();

    Classroom findById(int id);

    void update(int id, Classroom classroom);

    void delete(int id);
}
