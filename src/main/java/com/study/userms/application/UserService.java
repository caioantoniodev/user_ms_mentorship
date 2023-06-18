package com.study.userms.application;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.userms.adapters.inbound.dto.UserRequest;
import com.study.userms.adapters.inbound.dto.UserResponse;
import com.study.userms.domain.UserEntity;
import com.study.userms.ports.inbound.UserServicePortIn;
import com.study.userms.ports.outbound.UserDatabaseOperationsPortOut;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

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

        return users.stream()
                .map(user -> new UserResponse(user.getId(), user.getName(), user.getAge(), user.getUpdatedAt(), user.getCreatedAt()))
                .toList();
    }

    @Override
    public UserResponse findUserById(String id) {
        var user = userDatabaseOperationsPortOut.findById(id);

        return new UserResponse(user.getId(), user.getName(), user.getAge(), user.getUpdatedAt(), user.getCreatedAt());
    }

    @Override
    public void updatePartialUser(String id, Map<String, Object> requestData) {
        var user = userDatabaseOperationsPortOut.findById(id);

        merge(requestData, user);

        userDatabaseOperationsPortOut.save(user);
    }

    private void merge(Map<String, Object> requestData, UserEntity userEntity) {
        var objectMapper = new ObjectMapper();
        var userRequest = objectMapper.convertValue(requestData, UserEntity.class);

        requestData.forEach((propertyName, propertyValue) -> {
            var foundField = ReflectionUtils.findField(UserEntity.class, propertyName);
            isAllowedField(foundField);
            foundField.setAccessible(true);

            var foundValue = ReflectionUtils.getField(foundField, userRequest);
            ReflectionUtils.setField(foundField, userEntity, foundValue);
        });
    }

    private void isAllowedField(Field field) {
        if (field == null)
            throw new RuntimeException("Field not exists");

        if (field.getName().equalsIgnoreCase("id"))
            throw new RuntimeException("Field private");
    }
}
