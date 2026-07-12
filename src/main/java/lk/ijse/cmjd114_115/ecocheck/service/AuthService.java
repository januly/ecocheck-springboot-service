package lk.ijse.cmjd114_115.ecocheck.service;

import lk.ijse.cmjd114_115.ecocheck.dto.AuthResponseDTO;
import lk.ijse.cmjd114_115.ecocheck.dto.LoginDTO;
import lk.ijse.cmjd114_115.ecocheck.dto.UserDTO;

public interface AuthService {
    UserDTO register(UserDTO userDTO);

    AuthResponseDTO login(LoginDTO loginDTO);
}

