package lk.ijse.cmjd114_115.ecocheck.dao;

import lk.ijse.cmjd114_115.ecocheck.entity.GoalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GoalDAO extends JpaRepository<GoalEntity, String> {
    List<GoalEntity> findByUserUserId(String userId);
}

