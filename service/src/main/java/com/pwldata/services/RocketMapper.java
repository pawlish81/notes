package com.pwldata.services;

import com.pwl.rocket_sim.api.v1.model.*;
import com.pwldata.domain.AuditableEntity;
import com.pwldata.domain.RocketEntity;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;


@Mapper
@Service
public interface RocketMapper {

    RocketMapper INSTANCE = Mappers.getMapper(RocketMapper.class);

    @InheritConfiguration(name = "mapAuditableEntityToRocket")
    Rocket rocketEntityToRocket(RocketEntity entity);

    @Mapping(target = "id", expression = "java(java.util.UUID.randomUUID())")
    RocketEntity rocketToNewRocketEntity(RocketBody rocketBody);

    @Mapping(target = "id", expression = "java(java.util.UUID.randomUUID())")
    RocketEntity rocketToNewRocketEntity(NewRocket newRocket);

    void rocketToUpdateRocketEntity(UpdateRocket newRocket,@MappingTarget RocketEntity entity);

    RocketEntity rocketFilterToRocketEntity(GetRocketsFilter rocketBody);

    @Mapping(target = "audit.createdByUser", source = "createdByUser")
    @Mapping(target = "audit.modifiedByUser", source = "modifiedByUser")
    @Mapping(target = "audit.modifiedDate", source = "lastModifiedDate")
    @Mapping(target = "audit.createdDate", source = "createdDate")
    Rocket mapAuditableEntityToRocket(AuditableEntity auditableEntity);

    default OffsetDateTime mapLocalDateTimeToOffsetDateTime(LocalDateTime localDateTime) {
        if (localDateTime != null) {
            ZoneId zoneId = ZoneId.systemDefault();
            return localDateTime.atZone(zoneId).toOffsetDateTime();
        }
        return null;
    }

    default LocalDateTime mapOffsetDateTimeToLocalDateTime(OffsetDateTime offsetDateTime) {
        if (offsetDateTime != null) {
            return offsetDateTime.toLocalDateTime();

        }
        return null;
    }
}