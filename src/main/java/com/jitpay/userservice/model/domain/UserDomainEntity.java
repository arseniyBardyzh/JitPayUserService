package com.jitpay.userservice.model.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Domain entity to save User Data
 */
@Entity
@Table(name = "DOMAIN_USER")
public class UserDomainEntity {

    /**
     * Identifier of User
     */
    @Id
    private String id;
    /**
     * First Name
     */
    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;
    /**
     * Second Name
     */
    @Column(name = "LAST_NAME", nullable = false)
    private String secondName;
    /**
     * Email
     */
    @Column(name = "EMAIL")
    private String email;
    /**
     * Last Update time
     */
    @Column(name = "UPDATE_TIME")
    private LocalDateTime updateTime;

    @OneToMany(mappedBy = "userDomainEntity", fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private List<LocationDomainEntity> locationList = new ArrayList<>();

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<LocationDomainEntity> getLocationList() {
        return locationList;
    }

    public void setLocationList(List<LocationDomainEntity> locationList) {
        this.locationList = locationList;
    }

    public void addLocation(LocationDomainEntity location){
        this.locationList.add(location);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDomainEntity that = (UserDomainEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(secondName, that.secondName) &&
                Objects.equals(email, that.email) &&
                Objects.equals(updateTime, that.updateTime) &&
                Objects.equals(locationList, that.locationList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, secondName, email, updateTime, locationList);
    }

    @Override
    public String toString() {
        return "UserDomainEntity{" +
                "id='" + id + '\'' +
                "firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", email='" + email + '\'' +
                ", updateTime=" + updateTime +
                ", locationList=" + locationList +
                '}';
    }
}
