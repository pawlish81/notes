package com.pwldata.repositories;

import com.pwldata.domain.RocketEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface RocketRepository extends MongoRepository<RocketEntity, UUID> {
}
