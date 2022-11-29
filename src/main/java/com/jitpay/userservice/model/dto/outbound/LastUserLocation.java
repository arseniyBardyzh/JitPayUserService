package com.jitpay.userservice.model.dto.outbound;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jitpay.userservice.model.dto.Location;
import com.jitpay.userservice.model.dto.inbound.UserData;

import java.util.Objects;

/**
 * User Data with location
 */
public class LastUserLocation extends UserData {

    /**
     * User location
     */
    @JsonProperty("location")
    private Location location;

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
        if (!super.equals(o)) return false;
        LastUserLocation that = (LastUserLocation) o;
        return Objects.equals(location, that.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), location);
    }

    @Override
    public String toString() {
        return "UserLocation{" +
                "location=" + location +
                '}';
    }
}
