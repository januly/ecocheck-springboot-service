package lk.ijse.cmjd114_115.ecocheck.service;

import lk.ijse.cmjd114_115.ecocheck.dto.UserDTO;

import java.util.List;

public interface UserService {
    void saveUser(UserDTO user);

    UserDTO getSelectedUser(String userId);

    List<UserDTO> getAllUsers();

    void updateUser(String userId, UserDTO user);

    void deleteUser(String userId);
}

