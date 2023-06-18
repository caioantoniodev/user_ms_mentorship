package com.study.userms.adapters.inbound.http;

import com.study.userms.adapters.inbound.dto.UserRequest;
import com.study.userms.adapters.inbound.dto.UserResponse;
import com.study.userms.ports.inbound.UserServicePortIn;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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

    @PatchMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePartialUser(@PathVariable String id, @RequestBody Map<String, Object> requestData) {
        userServicePortIn.updatePartialUser(id, requestData);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserResponse> findAllUsers() {
        var users = userServicePortIn.findAllUsers();

        return users
                .stream()
                .map(user ->
                user.add(
                        linkTo(
                                methodOn(UserHttp.class).findUserById(user.getId())
                        ).withSelfRel()
                )
        ).toList();
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse findUserById(@PathVariable String id) {
        return userServicePortIn.findUserById(id);
    }
}
