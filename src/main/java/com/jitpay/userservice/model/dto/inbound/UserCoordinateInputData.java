package com.jitpay.userservice.model.dto.inbound;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jitpay.userservice.model.dto.Location;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Current User location
 */
public class UserCoordinateInputData {

    /**
     * User Identifier
     */
    @JsonProperty("userId")
    @NotBlank(message = "UserId must not be empty")
    private String userId;
    /**
     * date Time of creation data
     */
    @JsonProperty("createdOn")
    @NotNull(message = "createdOn must not be Null")
    private LocalDateTime createdOn;
    /**
     * coordinate of user device
     */
    @JsonProperty("location")
    @Valid
    private Location location;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

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
        UserCoordinateInputData that = (UserCoordinateInputData) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(createdOn, that.createdOn) &&
                Objects.equals(location, that.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, createdOn, location);
    }

    @Override
    public String toString() {
        return "UserInputData{" +
                "userId=" + userId +
                ", createdOn=" + createdOn +
                ", location=" + location +
                '}';
    }
}
