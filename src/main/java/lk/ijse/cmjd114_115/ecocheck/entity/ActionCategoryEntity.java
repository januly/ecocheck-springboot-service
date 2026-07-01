package lk.ijse.cmjd114_115.ecocheck.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "action_categories")
public class ActionCategoryEntity {
    @Id
    @Column(name = "category_id")
    private String categoryId;

    private String name;

    private String description;
}

