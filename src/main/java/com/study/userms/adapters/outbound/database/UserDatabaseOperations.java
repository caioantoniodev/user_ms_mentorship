package com.study.userms.adapters.outbound.database;

import com.study.userms.domain.UserEntity;
import com.study.userms.infra.repositories.UserRepository;
import com.study.userms.ports.outbound.UserDatabaseOperationsPortOut;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDatabaseOperations implements UserDatabaseOperationsPortOut {

    private final UserRepository userRepository;

    public UserDatabaseOperations(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public String save(UserEntity userEntity) {
        var user = userRepository.save(userEntity);
        return user.getId();
    }

    @Override
    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }
}
