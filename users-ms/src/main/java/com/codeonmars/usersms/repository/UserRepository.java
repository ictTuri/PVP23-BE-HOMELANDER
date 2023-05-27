package com.codeonmars.usersms.repository;

import com.codeonmars.usersms.model.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsername(String credential);

    Optional<UserEntity> findByEmail(String email);

    Optional<UserEntity> findByUsernameAndEmail(String username, String email);
}
