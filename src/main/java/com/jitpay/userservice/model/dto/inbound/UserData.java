package com.jitpay.userservice.model.dto.inbound;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

/**
 * Common User Data
 */
public class UserData extends UserIdentifier{
    /**
     * User email
     */
    @JsonProperty("email")
    @NotBlank(message = "email must not be Null")
    @Email
    private String email;
    /**
     * User First Name
     */
    @JsonProperty("firstName")
    @NotBlank(message = "firstName must not be Null")
    private String firstName;
    /**
     * User Second Name
     */
    @JsonProperty("secondName")
    @NotBlank(message = "secondName must not be Null")
    private String secondName;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        UserData userData = (UserData) o;
        return Objects.equals(email, userData.email) && Objects.equals(firstName, userData.firstName) && Objects.equals(secondName, userData.secondName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), email, firstName, secondName);
    }

    @Override
    public String toString() {
        return "UserData{" +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                '}';
    }
}
