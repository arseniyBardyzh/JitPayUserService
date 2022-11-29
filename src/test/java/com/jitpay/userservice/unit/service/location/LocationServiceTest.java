package com.jitpay.userservice.unit.service.location;

import com.jitpay.userservice.mapper.location.LocationMapper;
import com.jitpay.userservice.mapper.location.LocationMapperImpl;
import com.jitpay.userservice.model.domain.LocationDomainEntity;
import com.jitpay.userservice.model.domain.UserDomainEntity;
import com.jitpay.userservice.model.dto.inbound.UserCoordinateInputData;
import com.jitpay.userservice.model.dto.outbound.LastUserLocation;
import com.jitpay.userservice.model.dto.outbound.UserLocation;
import com.jitpay.userservice.repository.storage.location.LocationDbRepository;
import com.jitpay.userservice.repository.storage.user.UserDbRepository;
import com.jitpay.userservice.repository.storage.user.exception.UserRepositoryException;
import com.jitpay.userservice.service.location.impl.LocationServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import static com.jitpay.userservice.unit.helper.EntityCreator.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LocationServiceTest {
    @Mock
    private LocationDbRepository locationDbRepository;
    @Spy
    private LocationMapper locationMapper = new LocationMapperImpl();
    @Mock
    private UserDbRepository userDbRepository;

    @InjectMocks
    LocationServiceImpl locationService;

    @Test
    void saveLocationData_ShouldSaveLocationData_WhenDataIsCorrect() throws UserRepositoryException {
        UserCoordinateInputData userInput = createUserCoordinateInpData();
        UserDomainEntity domainUser = createDomainUser();
        when(userDbRepository.findById(userInput.getUserId())).thenReturn(Optional.of(domainUser));

        locationService.saveLocationData(userInput);

    }

    @Test
    void saveLocationData_ShouldThrowAnException_WhenUserNotFound() throws UserRepositoryException {
        UserCoordinateInputData userInput = createUserCoordinateInpData();
        when(userDbRepository.findById(userInput.getUserId())).thenReturn(Optional.empty());

        assertThrows(UserRepositoryException.class, ()-> locationService.saveLocationData(userInput));

    }

    @Test
    void getLastLocationByUserId_ShouldReturnLastLocation_WhenLocationIsExist() throws UserRepositoryException {
        UserDomainEntity userDomainEntity = createDomainUser();
        LocationDomainEntity locationDomainEntity1 = createLocationDomainEntity();
        LocationDomainEntity locationDomainEntity2 = createLocationDomainEntity();
        LocationDomainEntity locationDomainEntity3 = createLocationDomainEntity();
        locationDomainEntity1.setRequestTime(LocalDateTime.now().minusDays(1));
        locationDomainEntity2.setRequestTime(LocalDateTime.now().minusDays(3));
        locationDomainEntity3.setRequestTime(LocalDateTime.now());
        locationDomainEntity3.setLatitude(BigDecimal.ONE);
        ArrayList<LocationDomainEntity> locations = new ArrayList<>();
        locations.add(locationDomainEntity1);
        locations.add(locationDomainEntity2);
        locations.add(locationDomainEntity3);
        userDomainEntity.setLocationList(locations);
        String userId = "1";
        when(userDbRepository.findById(userId)).thenReturn(Optional.of(userDomainEntity));
        LastUserLocation serviceResult = locationService.getLastLocationByUserId(userId);
        assertEquals(BigDecimal.ONE, serviceResult.getLocation().getLatitude());
    }

    @Test
    void getLastLocationByUserId_ShouldReturnEmptyLocation_WhenLocationIsNotExist() throws UserRepositoryException {
        UserDomainEntity userDomainEntity = createDomainUser();
        String userId = "1";
        when(userDbRepository.findById(userId)).thenReturn(Optional.of(userDomainEntity));
        LastUserLocation serviceResult = locationService.getLastLocationByUserId(userId);
        assertNull(serviceResult.getLocation().getLongitude());
    }

    @Test
    void getLastLocationByUserId_ShouldThrowAnException_WhenUserIsNotExist() {
        String userId = "1";
        when(userDbRepository.findById(userId)).thenReturn(Optional.empty());
        assertThrows(UserRepositoryException.class, () -> locationService.getLastLocationByUserId(userId));

    }

    @Test
    void getLocationsByDateTimeRangeAndUserId_ShouldReturnAllLocationsFromRange_LocationsAreExist() throws UserRepositoryException {

        UserDomainEntity userDomainEntity = createDomainUserWithLocations();
        LocalDate toDate = LocalDate.now();
        LocalDate fromDate = LocalDate.now().minusDays(1);
        String userId = "1";
        when(userDbRepository.findById(userId)).thenReturn(Optional.of(userDomainEntity));

        UserLocation serviceResult = locationService.getLocationsByDateTimeRangeAndUserId(userId, fromDate, toDate);
        assertEquals(1, serviceResult.getLocations().size());
    }

    @Test
    void getLocationsByDateTimeRangeAndUserId_ShouldReturnAllLocationsFromRange_WhenToDateIsNull() throws UserRepositoryException {

        UserDomainEntity userDomainEntity = createDomainUserWithLocations();
        LocalDate fromDate = LocalDate.now().minusDays(1);
        String userId = "1";
        when(userDbRepository.findById(userId)).thenReturn(Optional.of(userDomainEntity));

        UserLocation serviceResult = locationService.getLocationsByDateTimeRangeAndUserId(userId, fromDate, null);
        assertEquals(1, serviceResult.getLocations().size());
    }

    @Test
    void getLocationsByDateTimeRangeAndUserId_ShouldReturnEmptyLocationsList_WhenLocationDoesNotExist() throws UserRepositoryException {

        UserDomainEntity userDomainEntity = createDomainUserWithLocations();
        LocalDate toDate = LocalDate.now().minusDays(1000);
        LocalDate fromDate = LocalDate.now().minusDays(101);
        String userId = "1";
        when(userDbRepository.findById(userId)).thenReturn(Optional.of(userDomainEntity));

        UserLocation serviceResult = locationService.getLocationsByDateTimeRangeAndUserId(userId, fromDate, toDate);
        assertEquals(0, serviceResult.getLocations().size());
    }

    @Test
    void getLocationsByDateTimeRangeAndUserId_ShouldThrowException_WhenUserDoesNotExist() {

        LocalDate toDate = LocalDate.now().minusDays(1000);
        LocalDate fromDate = LocalDate.now().minusDays(101);
        String userId = "1";
        when(userDbRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(UserRepositoryException.class, () -> locationService.getLocationsByDateTimeRangeAndUserId(userId, fromDate, toDate));
    }
}
