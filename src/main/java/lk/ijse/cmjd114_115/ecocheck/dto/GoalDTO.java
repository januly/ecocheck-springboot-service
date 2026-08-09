package lk.ijse.cmjd114_115.ecocheck.dto;

import lk.ijse.cmjd114_115.ecocheck.dto.enums.GoalStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GoalDTO implements Serializable {
    private String goalId;
    private String title;
    private Double targetReduction;
    private LocalDate targetDate;
    private GoalStatus status;
    private String userId;
}

