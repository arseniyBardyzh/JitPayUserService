package com.jitpay.userservice.service.user.impl;

import com.jitpay.userservice.mapper.user.UserMapper;
import com.jitpay.userservice.model.domain.UserDomainEntity;
import com.jitpay.userservice.model.dto.inbound.UserData;
import com.jitpay.userservice.repository.storage.user.UserDbRepository;
import com.jitpay.userservice.repository.storage.user.exception.UserRepositoryException;
import com.jitpay.userservice.service.user.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

import static com.jitpay.userservice.repository.storage.user.exception.UserRepositoryException.EMAIL_USER_EXCEPTION;
import static com.jitpay.userservice.repository.storage.user.exception.UserRepositoryException.USER_NOT_FIND_EXCEPTION;

@Service
public class UserServiceImpl implements UserService {
    private final UserDbRepository userDbRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(final UserDbRepository userDbRepository,
                           final UserMapper userMapper) {
        this.userDbRepository = userDbRepository;
        this.userMapper = userMapper;
    }

    @Override
    @Transactional
    public String saveUser(UserData user) throws UserRepositoryException {
        Optional<UserDomainEntity> currentUserOpt = userDbRepository.findById(user.getUserId());
        UserDomainEntity userFromDto = userMapper.userDataDtoToDomainEntityMapper(user);
        if (currentUserOpt.isPresent()){
            UserDomainEntity currentUserFromDb = currentUserOpt.get();
            updateUser(currentUserFromDb, userFromDto);
            return currentUserFromDb.getId();
        }
        checkEmailUnique(user);
        return userDbRepository.save(userFromDto).getId();
    }

    private void checkEmailUnique(UserData user) throws UserRepositoryException {
        if(Boolean.TRUE.equals(userDbRepository.existsUserDomainEntitiesByEmail(user.getEmail()))){
            throw new UserRepositoryException(String.format(EMAIL_USER_EXCEPTION, user.getEmail()));
        }
    }

    @Override
    @Transactional
    public UserData getUserById(String userId) throws UserRepositoryException {
        UserDomainEntity domainUser = userDbRepository.findById(userId)
                                                        .orElseThrow(() ->
                                                                new UserRepositoryException(String.format(USER_NOT_FIND_EXCEPTION, userId)));
        return userMapper.userDomainEntityToDtoMapper(domainUser);
    }

    private void updateUser(UserDomainEntity userFromDb, UserDomainEntity userFromDto){
        if(!userFromDb.equals(userFromDto)){
            userFromDb.setFirstName(userFromDto.getFirstName());
            userFromDb.setSecondName(userFromDto.getSecondName());
            userFromDb.setUpdateTime(LocalDateTime.now());
        }
    }
}
