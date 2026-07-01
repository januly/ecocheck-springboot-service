package lk.ijse.cmjd114_115.ecocheck.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "climate_actions")
public class ClimateActionEntity {
    @Id
    @Column(name = "climate_action_id")
    private String climateActionId;

    private String title;

    private String description;

    @Column(name = "estimated_co2_reduction")
    private Double estimatedCO2Reduction;

    private Integer points;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private ActionCategoryEntity category;
}

