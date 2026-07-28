package lk.ijse.cmjd114_115.ecocheck.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClimateActionDTO implements Serializable {
    private String climateActionId;
    private String title;
    private String description;
    private Double estimatedCO2Reduction;
    private Integer points;
    private String categoryId;
}

