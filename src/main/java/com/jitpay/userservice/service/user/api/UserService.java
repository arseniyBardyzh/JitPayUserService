package com.jitpay.userservice.service.user.api;

import com.jitpay.userservice.model.dto.inbound.UserData;
import com.jitpay.userservice.repository.storage.user.exception.UserRepositoryException;

/**
 * User service Interface
 */
public interface UserService {
    /**
     * Save user in service DB
     * If user exist, user data will be update (by identifier)
     * @param user Input dto
     * @return User identifier
     */
    String saveUser(UserData user) throws UserRepositoryException;

    /**
     * Get User Dto by Identifier
     * @param userId user identifier
     * @return User Dto
     */
    UserData getUserById(String userId) throws UserRepositoryException;
}
