package lk.ijse.cmjd114_115.ecocheck.service;

import lk.ijse.cmjd114_115.ecocheck.dto.GoalDTO;

import java.util.List;

public interface GoalService {
    void createGoal(GoalDTO goal);

    GoalDTO getSelectedGoal(String goalId);

    List<GoalDTO> getGoals();

    List<GoalDTO> getGoalsByUser(String userId);

    void updateGoal(String goalId, GoalDTO goal);

    void deleteGoal(String goalId);
}

