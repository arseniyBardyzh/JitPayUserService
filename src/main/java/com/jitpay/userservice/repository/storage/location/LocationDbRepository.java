package com.jitpay.userservice.repository.storage.location;

import com.jitpay.userservice.model.domain.LocationDomainEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationDbRepository extends JpaRepository<LocationDomainEntity, String> {

}
