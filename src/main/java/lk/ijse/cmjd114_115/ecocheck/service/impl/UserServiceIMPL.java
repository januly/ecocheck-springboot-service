package lk.ijse.cmjd114_115.ecocheck.service.impl;

import lk.ijse.cmjd114_115.ecocheck.IDGenerate;
import lk.ijse.cmjd114_115.ecocheck.dao.UserDAO;
import lk.ijse.cmjd114_115.ecocheck.dto.UserDTO;
import lk.ijse.cmjd114_115.ecocheck.dto.enums.Role;
import lk.ijse.cmjd114_115.ecocheck.entity.UserEntity;
import lk.ijse.cmjd114_115.ecocheck.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceIMPL implements UserService {
    private final UserDAO userDAO;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void saveUser(UserDTO user) {
        UserEntity entity = new UserEntity();
        entity.setUserId(user.getUserId() == null ? IDGenerate.userId() : user.getUserId());
        entity.setName(user.getName());
        entity.setEmail(user.getEmail());
        entity.setPassword(passwordEncoder.encode(user.getPassword()));
        entity.setRole(user.getRole() == null ? Role.USER : user.getRole());
        userDAO.save(entity);
    }

    @Override
    public UserDTO getSelectedUser(String userId) {
        return toDTO(findUser(userId));
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userDAO.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    @Override
    public void updateUser(String userId, UserDTO user) {
        UserEntity entity = findUser(userId);
        entity.setName(user.getName());
        entity.setEmail(user.getEmail());
        if (user.getPassword() != null && !user.getPassword().isBlank()) {
            entity.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        if (user.getRole() != null) {
            entity.setRole(user.getRole());
        }
        userDAO.save(entity);
    }

    @Override
    public void deleteUser(String userId) {
        UserEntity entity = findUser(userId);
        userDAO.delete(entity);
    }

    private UserEntity findUser(String userId) {
        return userDAO.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }

    private UserDTO toDTO(UserEntity entity) {
        return new UserDTO(
                entity.getUserId(),
                entity.getName(),
                entity.getEmail(),
                null,
                entity.getRole()
        );
    }
}

