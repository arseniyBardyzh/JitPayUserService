package com.jitpay.userservice.web.controller;

import com.jitpay.userservice.model.dto.inbound.UserCoordinateInputData;
import com.jitpay.userservice.model.dto.outbound.LastUserLocation;
import com.jitpay.userservice.model.dto.outbound.UserLocation;
import com.jitpay.userservice.repository.storage.user.exception.UserRepositoryException;
import com.jitpay.userservice.service.location.api.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;

@RestController
@RequestMapping("/location")
public class LocationController {

    private final LocationService locationService;

    @Autowired
    public LocationController(final LocationService locationService){
        this.locationService = locationService;
    }

    @PostMapping()
    public void createNewLocationByUserId(@RequestBody @Valid UserCoordinateInputData userCoordinateInputData) throws UserRepositoryException {
        locationService.saveLocationData(userCoordinateInputData);
    }

    @GetMapping("/{userId}")
    public LastUserLocation getLatestLocationByUserId(@PathVariable String userId) throws UserRepositoryException {
        return locationService.getLastLocationByUserId(userId);
    }

    @GetMapping("/range/{userId}")
    public UserLocation getLocationByUserId(@PathVariable String userId,
                                            @RequestParam("from") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate from,
                                            @Nullable @RequestParam("to") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate to) throws UserRepositoryException {
        return locationService.getLocationsByDateTimeRangeAndUserId(userId, from, to);
    }


}
