package lk.ijse.cmjd114_115.ecocheck.dao;

import lk.ijse.cmjd114_115.ecocheck.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDAO extends JpaRepository<UserEntity, String> {
    Optional<UserEntity> findByEmail(String email);

    boolean existsByEmail(String email);
}

