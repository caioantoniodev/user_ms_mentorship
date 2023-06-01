package com.study.userms.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document("users")
public class UserEntity {

    @Id
    private String id;
    private String name;
    private String cpf;
    private Integer age;
    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCpf() {
        return cpf;
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

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String name;
        private String cpf;
        private Integer age;
        private LocalDateTime updatedAt;
        private LocalDateTime createdAt;

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withCpf(String cpf) {
            this.cpf = cpf;
            return this;
        }

        public Builder withAge(Integer age) {
            this.age = age;
            return this;
        }

        public Builder withUpdatedAt() {
            this.updatedAt = LocalDateTime.now();
            return this;
        }

        public Builder withCreatedAt() {
            this.createdAt = LocalDateTime.now();
            return this;
        }

        public UserEntity build() {
            UserEntity userEntity = new UserEntity();
            userEntity.age = this.age;
            userEntity.createdAt = this.createdAt;
            userEntity.name = this.name;
            userEntity.updatedAt = this.updatedAt;
            userEntity.cpf = this.cpf;
            return userEntity;
        }
    }
}
