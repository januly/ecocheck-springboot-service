package lk.ijse.cmjd114_115.ecocheck.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lk.ijse.cmjd114_115.ecocheck.dto.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "app_users")
public class UserEntity {
    @Id
    @Column(name = "user_id")
    private String userId;

    private String name;

    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;
}

