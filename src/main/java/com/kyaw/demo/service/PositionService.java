package com.kyaw.demo.service;

import com.kyaw.demo.domain.Position;

import java.util.List;

public interface PositionService {

    List<Position> listPosition();

    void register(Position position);

    Position findById(int id);

    void update(int id, Position position);

    void delete(int id);
}
