package com.jitpay.userservice.model.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Domain Entity to save Location Data
 */
@Entity
@Table(name = "LOCATION")
public class LocationDomainEntity {

    /**
     * Identifier entity
     */
    @Id
    @Column(name = "id", unique = true)
    private String id;
    /**
     * Latitude
     */
    @Column(name = "LATITUDE", nullable = false)
    private BigDecimal latitude;
    /**
     * Longitude
     */
    @Column(name = "LONGITUDE", nullable = false)
    private BigDecimal longitude;
    /**
     * Time of creation data
     */
    @Column(name = "TIME_STAMP")
    private LocalDateTime requestTime;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private UserDomainEntity userDomainEntity;

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public LocalDateTime getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(LocalDateTime requestTime) {
        this.requestTime = requestTime;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public UserDomainEntity getUserDomainEntity() {
        return userDomainEntity;
    }

    public void setUserDomainEntity(UserDomainEntity userDomainEntity) {
        this.userDomainEntity = userDomainEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LocationDomainEntity that = (LocationDomainEntity) o;
        return Objects.equals(latitude, that.latitude) &&
                Objects.equals(id, that.id) &&
                Objects.equals(longitude, that.longitude) &&
                Objects.equals(requestTime, that.requestTime) &&
                Objects.equals(userDomainEntity, that.userDomainEntity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, latitude, longitude, requestTime, userDomainEntity);
    }

    @Override
    public String toString() {
        return "LocationDomainEntity{" +
                "id='" + id + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", requestTime=" + requestTime +
                ", userDomainEntity=" + userDomainEntity +
                '}';
    }
}
