package lk.ijse.cmjd114_115.ecocheck.dto;

import lk.ijse.cmjd114_115.ecocheck.dto.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO implements Serializable {
    private String userId;
    private String name;
    private String email;
    private String password;
    private Role role;
}

