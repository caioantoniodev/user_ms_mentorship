package com.study.userms.adapters.inbound.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UserResponse(@JsonProperty("id") String id,
                           @JsonProperty("name") String name,
                           @JsonProperty("age") Integer age) {
}
