package com.jitpay.userservice.repository.storage.user;

import com.jitpay.userservice.model.domain.UserDomainEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDbRepository extends JpaRepository<UserDomainEntity, String> {
    /**
     * Check existing email
     * @param email - new user Email
     * @return exist or not exist
     */
    Boolean existsUserDomainEntitiesByEmail(String email);
}
