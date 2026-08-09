package lk.ijse.cmjd114_115.ecocheck.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserActionDTO implements Serializable {
    private String userActionId;
    private Integer quantity;
    private Double totalReduction;
    private LocalDate completedDate;
    private String userId;
    private String actionId;
}

