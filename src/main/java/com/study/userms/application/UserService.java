package com.study.userms.application;

import com.study.userms.adapters.inbound.dto.UserRequest;
import com.study.userms.domain.UserEntity;
import com.study.userms.ports.inbound.UserServicePortIn;
import com.study.userms.ports.outbound.UserDatabaseOperationsPortOut;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserServicePortIn {

    private final UserDatabaseOperationsPortOut userDatabaseOperationsPortOut;

    public UserService(UserDatabaseOperationsPortOut userDatabaseOperationsPortOut) {
        this.userDatabaseOperationsPortOut = userDatabaseOperationsPortOut;
    }

    @Override
    public String createUser(UserRequest userRequest) {
        var user = UserEntity.builder()
                        .withName(userRequest.name())
                        .withAge(userRequest.age())
                        .withCpf(userRequest.cpf())
                        .withUpdatedAt()
                        .withCreatedAt().build();

        return userDatabaseOperationsPortOut.save(user);
    }
}
