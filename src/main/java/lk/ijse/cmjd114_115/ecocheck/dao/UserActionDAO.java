package lk.ijse.cmjd114_115.ecocheck.dao;

import lk.ijse.cmjd114_115.ecocheck.entity.UserActionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserActionDAO extends JpaRepository<UserActionEntity, String> {
    List<UserActionEntity> findByUserUserId(String userId);
}

