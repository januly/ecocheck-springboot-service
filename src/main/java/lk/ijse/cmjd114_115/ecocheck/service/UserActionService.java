package lk.ijse.cmjd114_115.ecocheck.service;

import lk.ijse.cmjd114_115.ecocheck.dto.UserActionDTO;

import java.util.List;

public interface UserActionService {
    void createUserAction(UserActionDTO userAction);

    UserActionDTO getSelectedUserAction(String userActionId);

    List<UserActionDTO> getAllUserActions();

    List<UserActionDTO> getUserActionsByUser(String userId);

    void updateUserAction(String userId, UserActionDTO userActionDTO);

    void deleteUserAction(String userActionId);
}

