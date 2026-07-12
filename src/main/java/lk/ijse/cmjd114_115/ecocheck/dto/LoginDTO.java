package lk.ijse.cmjd114_115.ecocheck.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginDTO implements Serializable {
    private String email;
    private String password;
}

