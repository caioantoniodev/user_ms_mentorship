package com.study.userms.ports.outbound;

import com.study.userms.domain.UserEntity;

public interface UserDatabaseOperationsPortOut {
    String save(UserEntity userEntity);
}
