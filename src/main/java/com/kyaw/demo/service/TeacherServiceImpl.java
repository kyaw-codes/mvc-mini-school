package com.kyaw.demo.service;

import com.kyaw.demo.domain.Teacher;
import com.kyaw.demo.repository.TeacherRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;

    public TeacherServiceImpl(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Override
    public List<Teacher> listTeachers() {
        return teacherRepository.findAll();
    }

    @Override
    public void save(Teacher teacher) {
        teacherRepository.save(teacher);
    }

    @Override
    public Teacher findById(int id) {
        return teacherRepository.findById(id).get();
    }

    @Override
    @Transactional
    public void update(int id, Teacher teacher) {
        Teacher t = findById(id);
        t.setName(teacher.getName());
        t.setClassrooms(teacher.getClassrooms());
        t.setPersonalInfo(teacher.getPersonalInfo());
        t.setPosition(teacher.getPosition());
    }

    @Override
    public void delete(int id) {
        teacherRepository.deleteById(id);
    }
}
