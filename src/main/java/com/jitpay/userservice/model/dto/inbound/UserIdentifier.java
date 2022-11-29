package com.jitpay.userservice.model.dto.inbound;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

public class UserIdentifier {
    /**
     * User Identifier
     */
    @JsonProperty("userId")
    @NotBlank(message = "userId must not be Null")
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserIdentifier that = (UserIdentifier) o;
        return Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }

    @Override
    public String toString() {
        return "UserIdentifier{" +
                "userId='" + userId + '\'' +
                '}';
    }
}
