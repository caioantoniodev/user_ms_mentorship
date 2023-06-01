package com.study.userms.adapters.inbound.http;

import com.study.userms.adapters.inbound.dto.UserRequest;
import com.study.userms.ports.inbound.UserServicePortIn;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserHttp {

    @Autowired
    private final UserServicePortIn userServicePortIn;

    public UserHttp(UserServicePortIn userServicePortIn) {
        this.userServicePortIn = userServicePortIn;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody UserRequest userRequest, HttpServletResponse httpServletResponse) {
        String createdUserId = userServicePortIn.createUser(userRequest);
        httpServletResponse.setHeader("Location", createdUserId);
    }

}
