package com.study.userms.ports.outbound;

import com.study.userms.domain.UserEntity;

import java.util.List;

public interface UserDatabaseOperationsPortOut {
    String save(UserEntity userEntity);
    List<UserEntity> findAll();
    UserEntity findById(String id);
}
