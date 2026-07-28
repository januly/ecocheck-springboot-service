package lk.ijse.cmjd114_115.ecocheck.service.impl;

import lk.ijse.cmjd114_115.ecocheck.IDGenerate;
import lk.ijse.cmjd114_115.ecocheck.dao.ActionCategoryDAO;
import lk.ijse.cmjd114_115.ecocheck.dto.ActionCategoryDTO;
import lk.ijse.cmjd114_115.ecocheck.entity.ActionCategoryEntity;
import lk.ijse.cmjd114_115.ecocheck.service.ActionCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ActionCategoryIMPL implements ActionCategoryService {
    private final ActionCategoryDAO actionCategoryDAO;

    @Override
    public void saveActionCategory(ActionCategoryDTO actionCategory) {
        ActionCategoryEntity entity = new ActionCategoryEntity();
        entity.setCategoryId(actionCategory.getCategoryId() == null ? IDGenerate.actionCategoryId() : actionCategory.getCategoryId());
        entity.setName(actionCategory.getName());
        entity.setDescription(actionCategory.getDescription());
        actionCategoryDAO.save(entity);
    }

    @Override
    public ActionCategoryDTO getSelectedActionCategory(String actionCategoryId) {
        return toDTO(findCategory(actionCategoryId));
    }

    @Override
    public List<ActionCategoryDTO> getAllActionCategories() {
        return actionCategoryDAO.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    @Override
    public void updateActionCategory(String actionCategoryId, ActionCategoryDTO actionCategory) {
        ActionCategoryEntity entity = findCategory(actionCategoryId);
        if (actionCategory.getName() != null) {
            entity.setName(actionCategory.getName());
        }
        if (actionCategory.getDescription() != null) {
            entity.setDescription(actionCategory.getDescription());
        }
        actionCategoryDAO.save(entity);
    }

    @Override
    public void deleteActionCategory(String actionCategoryId) {
        actionCategoryDAO.delete(findCategory(actionCategoryId));
    }

    private ActionCategoryEntity findCategory(String actionCategoryId) {
        return actionCategoryDAO.findById(actionCategoryId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Action category not found"));
    }

    private ActionCategoryDTO toDTO(ActionCategoryEntity entity) {
        return new ActionCategoryDTO(
                entity.getCategoryId(),
                entity.getName(),
                entity.getDescription()
        );
    }
}

