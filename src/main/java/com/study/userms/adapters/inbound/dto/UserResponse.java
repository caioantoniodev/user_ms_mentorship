package com.study.userms.adapters.inbound.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@JsonPropertyOrder({
        "id",
        "name",
        "age",
        "created_at",
        "updated_at"
})
public class UserResponse extends RepresentationModel<UserResponse> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    public UserResponse(){}

    public UserResponse(String id, String name, Integer age, LocalDateTime updatedAt, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
    }

    private String id;
    private String name;
    private Integer age;
    @JsonProperty("created_at")
    private LocalDateTime createdAt;
    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
