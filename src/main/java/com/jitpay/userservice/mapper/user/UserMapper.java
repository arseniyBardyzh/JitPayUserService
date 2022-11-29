package com.jitpay.userservice.mapper.user;

import com.jitpay.userservice.model.domain.UserDomainEntity;
import com.jitpay.userservice.model.dto.inbound.UserData;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(source = "id", target = "userId")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "firstName", target = "firstName")
    @Mapping(source = "secondName", target = "secondName")
    UserData userDomainEntityToDtoMapper(UserDomainEntity userDomainEntity);

    @Mapping(source = "userId", target = "id")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "firstName", target = "firstName")
    @Mapping(source = "secondName", target = "secondName")
    UserDomainEntity userDataDtoToDomainEntityMapper(UserData userData);
}
