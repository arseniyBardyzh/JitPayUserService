package com.jitpay.userservice.unit.helper;

import com.jitpay.userservice.model.domain.LocationDomainEntity;
import com.jitpay.userservice.model.domain.UserDomainEntity;
import com.jitpay.userservice.model.dto.Location;
import com.jitpay.userservice.model.dto.inbound.UserCoordinateInputData;
import com.jitpay.userservice.model.dto.inbound.UserData;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Class to create mock Entity for tests
 */
public class EntityCreator {
    public static UserData createUserData(){
        UserData userData = new UserData();
        userData.setUserId("1");
        userData.setEmail("1@q.tv");
        userData.setFirstName("First");
        userData.setSecondName("Second");
        return userData;
    }
    public static UserDomainEntity createDomainUser(){
        UserDomainEntity userDomainEntity = new UserDomainEntity();
        userDomainEntity.setId("1");
        userDomainEntity.setEmail("1@q.tv");
        userDomainEntity.setFirstName("FirstDomain");
        userDomainEntity.setSecondName("SecondDomain");
        return userDomainEntity;
    }
    public static UserDomainEntity createDomainUserWithLocations(){
        UserDomainEntity userDomainEntity = new UserDomainEntity();
        userDomainEntity.setId("1");
        userDomainEntity.setEmail("1@q.tv");
        userDomainEntity.setFirstName("FirstDomain");
        userDomainEntity.setSecondName("SecondDomain");
        LocationDomainEntity location1 = createLocationDomainEntity();
        LocationDomainEntity location2 = createLocationDomainEntity();
        LocationDomainEntity location3 = createLocationDomainEntity();
        location1.setRequestTime(LocalDateTime.now().minusDays(100));
        location2.setRequestTime(LocalDateTime.now());
        location3.setRequestTime(LocalDateTime.now().plusDays(100));
        userDomainEntity.addLocation(location1);
        userDomainEntity.addLocation(location2);
        userDomainEntity.addLocation(location3);
        return userDomainEntity;
    }

    public static LocationDomainEntity createLocationDomainEntity(){
        LocationDomainEntity locationDomainEntity = new LocationDomainEntity();
        locationDomainEntity.setId("1");
        locationDomainEntity.setLatitude(BigDecimal.valueOf(22));
        locationDomainEntity.setLongitude(BigDecimal.valueOf(33));
        return locationDomainEntity;
    }

    public static UserCoordinateInputData createUserCoordinateInpData(){
        UserCoordinateInputData userCoordinateInputData = new UserCoordinateInputData();
        userCoordinateInputData.setUserId("1");
        userCoordinateInputData.setCreatedOn(LocalDateTime.now());
        userCoordinateInputData.setLocation(createLocation());
        return userCoordinateInputData;
    }

    public static Location createLocation(){
        Location location = new Location();
        location.setLatitude(BigDecimal.valueOf(22));
        location.setLongitude(BigDecimal.valueOf(33));
        return location;
    }
}
