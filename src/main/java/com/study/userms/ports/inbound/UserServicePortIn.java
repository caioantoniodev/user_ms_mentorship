package com.study.userms.ports.inbound;

import com.study.userms.adapters.inbound.dto.UserRequest;

public interface UserServicePortIn {

    String createUser(UserRequest userRequest);
}
