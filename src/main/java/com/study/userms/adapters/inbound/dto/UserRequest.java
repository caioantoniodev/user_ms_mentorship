package com.study.userms.adapters.inbound.dto;

public record UserRequest(String name, Integer age, String cpf) {
    @Override
    public String toString() {
        return "UserRequest{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", cpf='" + cpf + '\'' +
                '}';
    }
}
