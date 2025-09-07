package org.yasmani.io.examplesecuritysprinbbootv1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.yasmani.io.examplesecuritysprinbbootv1.entity.UserEntity;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);
}
