package com.jitpay.userservice.model.dto.outbound;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jitpay.userservice.model.dto.inbound.UserIdentifier;

import java.util.List;
import java.util.Objects;

/**
 * Data about User locations
 */
public class UserLocation extends UserIdentifier {
    /**
     * Locations data
     */
    @JsonProperty("locations")
    private List<LocationWithTime> locations;

    public List<LocationWithTime> getLocations() {
        return locations;
    }

    public void setLocations(List<LocationWithTime> locations) {
        this.locations = locations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        UserLocation that = (UserLocation) o;
        return Objects.equals(locations, that.locations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), locations);
    }

    @Override
    public String toString() {
        return "UserLocation{" +
                ", locations=" + locations +
                '}';
    }
}
