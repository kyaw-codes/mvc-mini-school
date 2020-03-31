package com.kyaw.demo.service;

import com.kyaw.demo.domain.Position;
import com.kyaw.demo.repository.PositionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PositionServiceImpl implements PositionService {

    private final PositionRepository positionRepository;

    public PositionServiceImpl(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
    }

    @Override
    public List<Position> listPosition() {
        return positionRepository.findAll();
    }

    @Override
    public void register(Position position) {
        positionRepository.save(position);
    }

    @Override
    public Position findById(int id) {
        return positionRepository.findById(id).get();
    }

    @Override
    @Transactional
    public void update(int id, Position position) {
        Position p = findById(id);
        p.setName(position.getName());
        p.setSalary(position.getSalary());
    }

    @Override
    public void delete(int id) {
        positionRepository.deleteById(id);
    }
}
