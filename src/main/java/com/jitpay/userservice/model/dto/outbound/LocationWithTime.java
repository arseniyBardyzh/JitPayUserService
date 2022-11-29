package com.jitpay.userservice.model.dto.outbound;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jitpay.userservice.model.dto.Location;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Dto for outbound services
 */
public class LocationWithTime {
    /**
     * Time of creation location
     */
    @JsonProperty("createdOn")
    private LocalDateTime createdOn;
    /**
     * Location Data
     */
    @JsonProperty("location")
    private Location location;

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LocationWithTime that = (LocationWithTime) o;
        return Objects.equals(createdOn, that.createdOn) && Objects.equals(location, that.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(createdOn, location);
    }

    @Override
    public String toString() {
        return "LocationWithTime{" +
                "createdOn=" + createdOn +
                ", location=" + location +
                '}';
    }
}
