package com.study.userms.ports.inbound;

import com.study.userms.adapters.inbound.dto.UserRequest;
import com.study.userms.adapters.inbound.dto.UserResponse;

import java.util.List;
import java.util.Map;

public interface UserServicePortIn {

    String createUser(UserRequest userRequest);
    List<UserResponse> findAllUsers();
    UserResponse findUserById(String id);
    void updatePartialUser(String id, Map<String, Object> requestData);
}
