package com.jitpay.userservice.service.location.impl;

import com.jitpay.userservice.mapper.location.LocationMapper;
import com.jitpay.userservice.model.domain.LocationDomainEntity;
import com.jitpay.userservice.model.domain.UserDomainEntity;
import com.jitpay.userservice.model.dto.inbound.UserCoordinateInputData;
import com.jitpay.userservice.model.dto.outbound.LastUserLocation;
import com.jitpay.userservice.model.dto.outbound.UserLocation;
import com.jitpay.userservice.repository.storage.location.LocationDbRepository;
import com.jitpay.userservice.repository.storage.user.UserDbRepository;
import com.jitpay.userservice.repository.storage.user.exception.UserRepositoryException;
import com.jitpay.userservice.service.location.api.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.jitpay.userservice.repository.storage.user.exception.UserRepositoryException.USER_NOT_FIND_EXCEPTION;

@Service
public class LocationServiceImpl implements LocationService {

    private final LocationDbRepository locationDbRepository;
    private final LocationMapper locationMapper;
    private final UserDbRepository userDbRepository;

    @Autowired
    public LocationServiceImpl(final LocationDbRepository locationDbRepository,
                               final LocationMapper locationMapper,
                               final UserDbRepository userDbRepository) {
        this.locationDbRepository = locationDbRepository;
        this.locationMapper = locationMapper;
        this.userDbRepository = userDbRepository;
    }

    @Override
    @Transactional
    public void saveLocationData(UserCoordinateInputData location) throws UserRepositoryException {
        UserDomainEntity user = userDbRepository.findById(location.getUserId())
                                                .orElseThrow(() ->
                                                        new UserRepositoryException(String.format(USER_NOT_FIND_EXCEPTION, location.getUserId())));
        LocationDomainEntity locationDomainEntity = locationMapper.userCoordinateInputDataToDomainEntity(location);
        locationDomainEntity.setUserDomainEntity(user);
        locationDbRepository.save(locationDomainEntity);
    }

    @Override
    @Transactional
    public LastUserLocation getLastLocationByUserId(String userId) throws UserRepositoryException {
        UserDomainEntity user = userDbRepository.findById(userId)
                                                .orElseThrow(() ->
                                                        new UserRepositoryException(String.format(USER_NOT_FIND_EXCEPTION, userId)));
        LocationDomainEntity domainLastLocation = user.getLocationList().stream()
                                            .max(Comparator.comparing(LocationDomainEntity::getRequestTime))
                                            .orElse(new LocationDomainEntity());
        return locationMapper.locationAndUserDomainToDtoEntity(domainLastLocation, user);
    }

    @Override
    public UserLocation getLocationsByDateTimeRangeAndUserId(String userId,
                                                                   LocalDate fromDate,
                                                                   LocalDate toDate) throws UserRepositoryException {
        UserDomainEntity user = userDbRepository.findById(userId)
                .orElseThrow(() ->
                        new UserRepositoryException(String.format(USER_NOT_FIND_EXCEPTION, userId)));
        LocalDate finalToDate = toDate == null ? LocalDate.now() : toDate;
        List<LocationDomainEntity> domainLastLocationList = user.getLocationList().stream()
                .filter(l -> l.getRequestTime().toLocalDate().isBefore(finalToDate.plusDays(1)) &&
                            l.getRequestTime().toLocalDate().isAfter(fromDate.minusDays(1)))
                .collect(Collectors.toList());
        return createUserLocation(domainLastLocationList, user);
    }

    private UserLocation createUserLocation(List<LocationDomainEntity> domainLastLocationList, UserDomainEntity user){
        UserLocation userLocation = new UserLocation();
        userLocation.setUserId(user.getId());
        userLocation.setLocations(locationMapper.locationListToLocationWithTimeListEntity(domainLastLocationList));

        return userLocation;
    }
}
