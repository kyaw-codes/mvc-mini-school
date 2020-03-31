package com.kyaw.demo.service;

import com.kyaw.demo.domain.Classroom;
import com.kyaw.demo.repository.ClassroomRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClassroomServiceImpl implements ClassroomService {

    private final ClassroomRepository classroomRepository;

    public ClassroomServiceImpl(ClassroomRepository classroomRepository) {
        this.classroomRepository = classroomRepository;
    }

    @Override
    public void register(Classroom classroom) {
        classroomRepository.save(classroom);
    }

    @Override
    public List<Classroom> listClassroom() {
        return classroomRepository.findAll();
    }

    @Override
    public Classroom findById(int id) {
        return classroomRepository.findById(id).get();
    }

    @Override
    @Transactional
    public void update(int id, Classroom classroom) {
        Classroom old = findById(id);
        old.setName(classroom.getName());
    }

    @Override
    public void delete(int id) {
        classroomRepository.deleteById(id);
    }

}
