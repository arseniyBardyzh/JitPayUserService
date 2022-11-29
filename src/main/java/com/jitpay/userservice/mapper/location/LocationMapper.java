package com.jitpay.userservice.mapper.location;

import com.jitpay.userservice.model.domain.LocationDomainEntity;
import com.jitpay.userservice.model.domain.UserDomainEntity;
import com.jitpay.userservice.model.dto.inbound.UserCoordinateInputData;
import com.jitpay.userservice.model.dto.outbound.LastUserLocation;
import com.jitpay.userservice.model.dto.outbound.LocationWithTime;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LocationMapper {

    @Named("locationWithTimeMapper")
    @Mapping(source = "requestTime", target = "createdOn")
    @Mapping(source = "latitude", target = "location.latitude")
    @Mapping(source = "longitude", target = "location.longitude")
    LocationWithTime locationToLocationWithTimeEntity(LocationDomainEntity location);

    @Named("listLocationWithTimeMapper")
    @IterableMapping(qualifiedByName = "locationWithTimeMapper")
    List<LocationWithTime> locationListToLocationWithTimeListEntity(List<LocationDomainEntity> locations);

    @Named("locationDomainToDtoEntity")
    @Mapping(source = "userDomainEntity.id", target = "userId")
    @Mapping(source = "userDomainEntity.firstName", target = "firstName")
    @Mapping(source = "userDomainEntity.secondName", target = "secondName")
    @Mapping(source = "userDomainEntity.email", target = "email")
    @Mapping(source = "locationDomainEntity.latitude", target = "location.latitude")
    @Mapping(source = "locationDomainEntity.longitude", target = "location.longitude")
    LastUserLocation locationAndUserDomainToDtoEntity(LocationDomainEntity locationDomainEntity,
                                                      UserDomainEntity userDomainEntity);

    @Mapping(source = "location.latitude", target = "latitude")
    @Mapping(source = "location.longitude", target = "longitude")
    @Mapping(target ="id",
            expression = "java(java.util.UUID.randomUUID().toString())")
    @Mapping(target = "requestTime", expression = "java(java.time.LocalDateTime.now())")
    LocationDomainEntity userCoordinateInputDataToDomainEntity(UserCoordinateInputData user);
}
