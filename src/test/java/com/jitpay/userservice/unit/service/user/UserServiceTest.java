package com.jitpay.userservice.unit.service.user;

import com.jitpay.userservice.mapper.user.UserMapper;
import com.jitpay.userservice.mapper.user.UserMapperImpl;
import com.jitpay.userservice.model.domain.UserDomainEntity;
import com.jitpay.userservice.model.dto.inbound.UserData;
import com.jitpay.userservice.repository.storage.user.UserDbRepository;
import com.jitpay.userservice.repository.storage.user.exception.UserRepositoryException;
import com.jitpay.userservice.service.user.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.jitpay.userservice.unit.helper.EntityCreator.createDomainUser;
import static com.jitpay.userservice.unit.helper.EntityCreator.createUserData;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserDbRepository userDbRepository;

    @Spy
    private UserMapper userMapper = new UserMapperImpl();

    @InjectMocks
    UserServiceImpl userService;

    @Test
    void saveUser_ShouldSaveUserInDB_WhenUserNotFoundByIdInDb() throws UserRepositoryException {
        UserData user = createUserData();
        UserDomainEntity domainUser = userMapper.userDataDtoToDomainEntityMapper(user);
        when(userDbRepository.findById(anyString())).thenReturn(Optional.empty());
        when(userDbRepository.existsUserDomainEntitiesByEmail(anyString())).thenReturn(false);
        when(userDbRepository.save(domainUser)).thenReturn(domainUser);
        String result = userService.saveUser(user);
        assertEquals(user.getUserId(), result);
    }

    @Test
    void saveUser_ShouldThrowAnException_WhenUserwithSameEmailIsAlreadyExist(){
        UserData user = createUserData();
        UserDomainEntity domainUser = userMapper.userDataDtoToDomainEntityMapper(user);
        when(userDbRepository.findById(anyString())).thenReturn(Optional.empty());
        when(userDbRepository.existsUserDomainEntitiesByEmail(anyString())).thenReturn(true);
        assertThrows(UserRepositoryException.class, () -> userService.saveUser(user));
    }

    @Test
    void saveUser_ShouldUpdateUserInDB_WhenUserFoundByIdInDb() throws UserRepositoryException {
        UserDomainEntity domainUserFromDb = createDomainUser();
        UserData user = createUserData();
        when(userDbRepository.findById(anyString())).thenReturn(Optional.of(domainUserFromDb));
        String result = userService.saveUser(user);
        assertEquals(user.getUserId(), result);
        assertEquals(user.getFirstName(), domainUserFromDb.getFirstName());
    }

    @Test
    void getUserById_shouldReturnUserData_WhenUserExistInDb() throws UserRepositoryException {
        UserDomainEntity domainUser = createDomainUser();
        when(userDbRepository.findById(anyString())).thenReturn(Optional.of(domainUser));

        UserData result = userService.getUserById("1");
        assertEquals(domainUser.getId(),result.getUserId());
    }

    @Test
    void getUserById_shouldThrowAnException_WhenUserNotFound() {
        when(userDbRepository.findById(anyString())).thenReturn(Optional.empty());
        assertThrows(UserRepositoryException.class, () -> userService.getUserById("1"));
    }
}
