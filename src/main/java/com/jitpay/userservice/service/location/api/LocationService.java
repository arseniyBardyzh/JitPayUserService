package com.jitpay.userservice.service.location.api;

import com.jitpay.userservice.model.dto.inbound.UserCoordinateInputData;
import com.jitpay.userservice.model.dto.outbound.LastUserLocation;
import com.jitpay.userservice.model.dto.outbound.UserLocation;
import com.jitpay.userservice.repository.storage.user.exception.UserRepositoryException;
import org.springframework.lang.Nullable;

import java.time.LocalDate;

/**
 * Interface of location service
 */
public interface LocationService {
    /**
     * Save location data by user identifier
     * @param userCoordinateInputData current location with user identifier
     * @return Entity identifier
     */
    void saveLocationData(UserCoordinateInputData userCoordinateInputData) throws UserRepositoryException;

    /**
     * Get last available location by user identifier
     * @param userId user identifier
     * @return UserLocation
     */
    LastUserLocation getLastLocationByUserId(String userId) throws UserRepositoryException;

    /**
     * Get all available location by user identifier and date time range
     * @param userId user identifier
     * @param fromDate from date time
     * @param toDate to date time
     * @return user locations
     */
    UserLocation getLocationsByDateTimeRangeAndUserId(String userId,
                                                    LocalDate fromDate,
                                                    @Nullable LocalDate toDate) throws UserRepositoryException;
}
