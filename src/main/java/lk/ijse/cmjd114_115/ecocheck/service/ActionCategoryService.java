package lk.ijse.cmjd114_115.ecocheck.service;

import lk.ijse.cmjd114_115.ecocheck.dto.ActionCategoryDTO;

import java.util.List;

public interface ActionCategoryService {
    void saveActionCategory(ActionCategoryDTO actionCategory);

    ActionCategoryDTO getSelectedActionCategory(String actionCategoryId);

    List<ActionCategoryDTO> getAllActionCategories();

    void updateActionCategory(String actionCategoryId, ActionCategoryDTO actionCategory);

    void deleteActionCategory(String actionCategoryId);
}

