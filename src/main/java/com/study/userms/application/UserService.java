package com.study.userms.application;

import com.study.userms.adapters.inbound.dto.UserRequest;
import com.study.userms.adapters.inbound.dto.UserResponse;
import com.study.userms.domain.UserEntity;
import com.study.userms.ports.inbound.UserServicePortIn;
import com.study.userms.ports.outbound.UserDatabaseOperationsPortOut;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<UserResponse> findAllUsers() {

        var users = userDatabaseOperationsPortOut.findAll();

        return users
                .stream()
                .map(user -> new UserResponse(user.getId(), user.getName(), user.getAge()))
                .collect(Collectors.toList());
    }
}
