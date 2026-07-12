package lk.ijse.cmjd114_115.ecocheck.dto;

import lk.ijse.cmjd114_115.ecocheck.dto.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuthResponseDTO implements Serializable {
    private String accessToken;
    private String tokenType;
    private long expiresIn;
    private String userId;
    private String name;
    private String email;
    private Role role;
}

