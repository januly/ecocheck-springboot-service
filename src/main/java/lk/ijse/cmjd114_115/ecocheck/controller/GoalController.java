package lk.ijse.cmjd114_115.ecocheck.controller;

import lk.ijse.cmjd114_115.ecocheck.dto.GoalDTO;
import lk.ijse.cmjd114_115.ecocheck.service.GoalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/goal")
@RequiredArgsConstructor
public class GoalController {
    private final GoalService goalService;

    @PostMapping
    ResponseEntity<Void> createGoal(@RequestBody GoalDTO goal) {
        goalService.createGoal(goal);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("{goalId}")
    ResponseEntity<GoalDTO> getSelectedGoal(@PathVariable String goalId) {
        return new ResponseEntity<>(goalService.getSelectedGoal(goalId), HttpStatus.OK);
    }

    @GetMapping
    ResponseEntity<List<GoalDTO>> getAllGoals(@RequestParam(required = false) String userId) {
        if (userId != null) {
            return new ResponseEntity<>(goalService.getGoalsByUser(userId), HttpStatus.OK);
        }
        return new ResponseEntity<>(goalService.getGoals(), HttpStatus.OK);
    }

    @PatchMapping("{goalId}")
    ResponseEntity<Void> updateGoal(@PathVariable String goalId, @RequestBody GoalDTO goal) {
        goalService.updateGoal(goalId, goal);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("{goalId}")
    ResponseEntity<Void> deleteGoal(@PathVariable String goalId) {
        goalService.deleteGoal(goalId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

