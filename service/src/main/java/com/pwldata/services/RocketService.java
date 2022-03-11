package com.pwldata.services;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.exact;


import com.pwldata.domain.RocketEntity;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pwldata.exceptions.RocketNotFoundException;
import com.pwldata.repositories.RocketRepository;

import java.util.Optional;
import java.util.UUID;

@Service
public class RocketService {

    private final RocketRepository rocketRepository;


    public RocketService(RocketRepository notesRepository) {
        this.rocketRepository = notesRepository;
    }

    public RocketEntity createRocket(RocketEntity entity) {
        return rocketRepository.save(entity);
    }

    public void deleteRocket(UUID id) {
        rocketRepository.deleteById(id);
    }

    public RocketEntity findRocketById(UUID id) {
        Optional<RocketEntity> note = rocketRepository.findById(id);
        return note.orElseThrow(() -> new RocketNotFoundException("Rocket with id {0} not found"));
    }

    public RocketEntity update(RocketEntity entity) {
        return rocketRepository.save(entity);
    }

    public Page<RocketEntity> findAll(RocketEntity entity, Pageable paging) {

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("id", exact())
                .withMatcher("type", exact())
                .withMatcher("name", exact());

        Example<RocketEntity> filter = Example.of(entity, matcher);
        return rocketRepository.findAll(filter, paging);

    }
}
