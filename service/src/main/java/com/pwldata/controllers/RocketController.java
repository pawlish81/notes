package com.pwldata.controllers;


import com.pwl.rocket_sim.api.v1.RocketsApi;
import com.pwl.rocket_sim.api.v1.model.*;
import com.pwldata.domain.RocketEntity;
import com.pwldata.services.RocketMapper;
import com.pwldata.services.RocketService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


@RestController()
public class RocketController implements RocketsApi {

    private final RocketService rocketService;

    public RocketController(RocketService rocketService) {
        this.rocketService = rocketService;
    }


    @Override
    public ResponseEntity<Rocket> addRocket(NewRocket newRocket) {
        RocketEntity entity = RocketMapper.INSTANCE.rocketToNewRocketEntity(newRocket);
        RocketEntity rocketEntity = rocketService.createRocket(entity);
        Rocket rocket = RocketMapper.INSTANCE.rocketEntityToRocket(rocketEntity);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(rocket);
    }

    @Override
    public ResponseEntity<Void> deleteRocket(UUID id) {
        return RocketsApi.super.deleteRocket(id);
    }


    @Override
    public ResponseEntity<RocketList> getRockets(Integer size, Integer page, GetRocketsFilter filter) {
        Pageable paging = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "buildDate"));

        RocketEntity entity = RocketMapper.INSTANCE.rocketFilterToRocketEntity(filter);

        Page<RocketEntity> result = rocketService.findAll(entity, paging);

        RocketList rocketLists = new RocketList();

        rocketLists.setItemList(result.toList()
                .stream()
                .map(RocketMapper.INSTANCE::rocketEntityToRocket)
                .collect(Collectors.toList()));


        rocketLists.setCurrentPage(result.getNumber());
        rocketLists.setTotalItems(result.getTotalElements());
        rocketLists.setTotalPage(result.getTotalPages());

        return ResponseEntity.ok(rocketLists);
    }

    @Override
    public ResponseEntity<Rocket> updateRocket(UpdateRocket updateRocket) {

        RocketEntity rocketById = rocketService.findRocketById(updateRocket.getId());

        RocketMapper.INSTANCE.rocketToUpdateRocketEntity(updateRocket, rocketById);

        RocketEntity updatedRocketEntity = rocketService.update(rocketById);

        Rocket rocket = RocketMapper.INSTANCE.rocketEntityToRocket(updatedRocketEntity);

        return ResponseEntity.ok(rocket);
    }


}
