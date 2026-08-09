package lk.ijse.cmjd114_115.ecocheck.service.impl;

import lk.ijse.cmjd114_115.ecocheck.IDGenerate;
import lk.ijse.cmjd114_115.ecocheck.dao.GoalDAO;
import lk.ijse.cmjd114_115.ecocheck.dao.UserDAO;
import lk.ijse.cmjd114_115.ecocheck.dto.GoalDTO;
import lk.ijse.cmjd114_115.ecocheck.dto.enums.GoalStatus;
import lk.ijse.cmjd114_115.ecocheck.entity.GoalEntity;
import lk.ijse.cmjd114_115.ecocheck.entity.UserEntity;
import lk.ijse.cmjd114_115.ecocheck.service.GoalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GoalServiceIMPL implements GoalService {
    private final GoalDAO goalDAO;
    private final UserDAO userDAO;

    @Override
    public void createGoal(GoalDTO goal) {
        GoalEntity entity = new GoalEntity();
        entity.setGoalId(goal.getGoalId() == null ? IDGenerate.goalId() : goal.getGoalId());
        entity.setTitle(goal.getTitle());
        entity.setTargetReduction(goal.getTargetReduction());
        entity.setTargetDate(goal.getTargetDate());
        entity.setStatus(goal.getStatus() == null ? GoalStatus.NOT_STARTED : goal.getStatus());
        entity.setUser(findUser(goal.getUserId()));
        goalDAO.save(entity);
    }

    @Override
    public GoalDTO getSelectedGoal(String goalId) {
        return toDTO(findGoal(goalId));
    }

    @Override
    public List<GoalDTO> getGoals() {
        return goalDAO.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    @Override
    public List<GoalDTO> getGoalsByUser(String userId) {
        return goalDAO.findByUserUserId(userId)
                .stream()
                .map(this::toDTO)
                .toList();
    }

    @Override
    public void updateGoal(String goalId, GoalDTO goal) {
        GoalEntity entity = findGoal(goalId);
        if (goal.getTitle() != null) {
            entity.setTitle(goal.getTitle());
        }
        if (goal.getTargetReduction() != null) {
            entity.setTargetReduction(goal.getTargetReduction());
        }
        if (goal.getTargetDate() != null) {
            entity.setTargetDate(goal.getTargetDate());
        }
        if (goal.getStatus() != null) {
            entity.setStatus(goal.getStatus());
        }
        if (goal.getUserId() != null) {
            entity.setUser(findUser(goal.getUserId()));
        }
        goalDAO.save(entity);
    }

    @Override
    public void deleteGoal(String goalId) {
        goalDAO.delete(findGoal(goalId));
    }

    private GoalEntity findGoal(String goalId) {
        return goalDAO.findById(goalId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Goal not found"));
    }

    private UserEntity findUser(String userId) {
        return userDAO.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }

    private GoalDTO toDTO(GoalEntity entity) {
        return new GoalDTO(
                entity.getGoalId(),
                entity.getTitle(),
                entity.getTargetReduction(),
                entity.getTargetDate(),
                entity.getStatus(),
                entity.getUser().getUserId()
        );
    }
}

