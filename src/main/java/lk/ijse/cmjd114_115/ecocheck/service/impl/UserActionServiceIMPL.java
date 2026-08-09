package lk.ijse.cmjd114_115.ecocheck.service.impl;

import lk.ijse.cmjd114_115.ecocheck.IDGenerate;
import lk.ijse.cmjd114_115.ecocheck.dao.ClimateActionDAO;
import lk.ijse.cmjd114_115.ecocheck.dao.UserActionDAO;
import lk.ijse.cmjd114_115.ecocheck.dao.UserDAO;
import lk.ijse.cmjd114_115.ecocheck.dto.UserActionDTO;
import lk.ijse.cmjd114_115.ecocheck.entity.ClimateActionEntity;
import lk.ijse.cmjd114_115.ecocheck.entity.UserActionEntity;
import lk.ijse.cmjd114_115.ecocheck.entity.UserEntity;
import lk.ijse.cmjd114_115.ecocheck.service.UserActionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserActionServiceIMPL implements UserActionService {
    private final UserActionDAO userActionDAO;
    private final UserDAO userDAO;
    private final ClimateActionDAO climateActionDAO;

    @Override
    public void createUserAction(UserActionDTO userAction) {
        UserEntity user = findUser(userAction.getUserId());
        ClimateActionEntity action = findClimateAction(userAction.getActionId());

        UserActionEntity entity = new UserActionEntity();
        entity.setUserActionId(userAction.getUserActionId() == null ? IDGenerate.userActionId() : userAction.getUserActionId());
        entity.setQuantity(userAction.getQuantity());
        entity.setTotalReduction(getTotalReduction(userAction, action));
        entity.setCompletedDate(userAction.getCompletedDate() == null ? LocalDate.now() : userAction.getCompletedDate());
        entity.setUser(user);
        entity.setAction(action);
        userActionDAO.save(entity);
    }

    @Override
    public UserActionDTO getSelectedUserAction(String userActionId) {
        return toDTO(findUserAction(userActionId));
    }

    @Override
    public List<UserActionDTO> getAllUserActions() {
        return userActionDAO.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    @Override
    public List<UserActionDTO> getUserActionsByUser(String userId) {
        return userActionDAO.findByUserUserId(userId)
                .stream()
                .map(this::toDTO)
                .toList();
    }

    @Override
    public void updateUserAction(String userActionId, UserActionDTO userActionDTO) {
        UserActionEntity entity = findUserAction(userActionId);
        if (userActionDTO.getQuantity() != null) {
            entity.setQuantity(userActionDTO.getQuantity());
        }
        if (userActionDTO.getCompletedDate() != null) {
            entity.setCompletedDate(userActionDTO.getCompletedDate());
        }
        if (userActionDTO.getUserId() != null) {
            entity.setUser(findUser(userActionDTO.getUserId()));
        }
        if (userActionDTO.getActionId() != null) {
            entity.setAction(findClimateAction(userActionDTO.getActionId()));
        }
        if (userActionDTO.getTotalReduction() != null) {
            entity.setTotalReduction(userActionDTO.getTotalReduction());
        } else if (userActionDTO.getQuantity() != null) {
            entity.setTotalReduction(entity.getAction().getEstimatedCO2Reduction() * entity.getQuantity());
        }
        userActionDAO.save(entity);
    }

    @Override
    public void deleteUserAction(String userActionId) {
        userActionDAO.delete(findUserAction(userActionId));
    }

    private Double getTotalReduction(UserActionDTO userAction, ClimateActionEntity action) {
        if (userAction.getTotalReduction() != null) {
            return userAction.getTotalReduction();
        }
        return action.getEstimatedCO2Reduction() * userAction.getQuantity();
    }

    private UserActionEntity findUserAction(String userActionId) {
        return userActionDAO.findById(userActionId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User action not found"));
    }

    private UserEntity findUser(String userId) {
        return userDAO.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }

    private ClimateActionEntity findClimateAction(String actionId) {
        return climateActionDAO.findById(actionId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Climate action not found"));
    }

    private UserActionDTO toDTO(UserActionEntity entity) {
        return new UserActionDTO(
                entity.getUserActionId(),
                entity.getQuantity(),
                entity.getTotalReduction(),
                entity.getCompletedDate(),
                entity.getUser().getUserId(),
                entity.getAction().getClimateActionId()
        );
    }
}
