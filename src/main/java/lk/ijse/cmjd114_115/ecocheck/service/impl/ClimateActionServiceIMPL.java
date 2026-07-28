package lk.ijse.cmjd114_115.ecocheck.service.impl;

import lk.ijse.cmjd114_115.ecocheck.IDGenerate;
import lk.ijse.cmjd114_115.ecocheck.dao.ActionCategoryDAO;
import lk.ijse.cmjd114_115.ecocheck.dao.ClimateActionDAO;
import lk.ijse.cmjd114_115.ecocheck.dto.ClimateActionDTO;
import lk.ijse.cmjd114_115.ecocheck.entity.ActionCategoryEntity;
import lk.ijse.cmjd114_115.ecocheck.entity.ClimateActionEntity;
import lk.ijse.cmjd114_115.ecocheck.service.ClimateActionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClimateActionServiceIMPL implements ClimateActionService {
    private final ClimateActionDAO climateActionDAO;
    private final ActionCategoryDAO actionCategoryDAO;

    @Override
    public void createClimateAction(ClimateActionDTO climateActionDTO) {
        ClimateActionEntity entity = new ClimateActionEntity();
        entity.setClimateActionId(climateActionDTO.getClimateActionId() == null ? IDGenerate.climateActionId() : climateActionDTO.getClimateActionId());
        entity.setTitle(climateActionDTO.getTitle());
        entity.setDescription(climateActionDTO.getDescription());
        entity.setEstimatedCO2Reduction(climateActionDTO.getEstimatedCO2Reduction());
        entity.setPoints(climateActionDTO.getPoints());
        entity.setCategory(findCategory(climateActionDTO.getCategoryId()));
        climateActionDAO.save(entity);
    }

    @Override
    public ClimateActionDTO getSelectedClimateAction(String climateActionId) {
        return toDTO(findClimateAction(climateActionId));
    }

    @Override
    public List<ClimateActionDTO> getClimateActions() {
        return climateActionDAO.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    @Override
    public void updateClimateActions(String climateActionId, ClimateActionDTO climateActionDTO) {
        ClimateActionEntity entity = findClimateAction(climateActionId);
        if (climateActionDTO.getTitle() != null) {
            entity.setTitle(climateActionDTO.getTitle());
        }
        if (climateActionDTO.getDescription() != null) {
            entity.setDescription(climateActionDTO.getDescription());
        }
        if (climateActionDTO.getEstimatedCO2Reduction() != null) {
            entity.setEstimatedCO2Reduction(climateActionDTO.getEstimatedCO2Reduction());
        }
        if (climateActionDTO.getPoints() != null) {
            entity.setPoints(climateActionDTO.getPoints());
        }
        if (climateActionDTO.getCategoryId() != null) {
            entity.setCategory(findCategory(climateActionDTO.getCategoryId()));
        }
        climateActionDAO.save(entity);
    }

    @Override
    public void deleteClimateActions(String climateActionId) {
        climateActionDAO.delete(findClimateAction(climateActionId));
    }

    private ClimateActionEntity findClimateAction(String climateActionId) {
        return climateActionDAO.findById(climateActionId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Climate action not found"));
    }

    private ActionCategoryEntity findCategory(String categoryId) {
        return actionCategoryDAO.findById(categoryId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Action category not found"));
    }

    private ClimateActionDTO toDTO(ClimateActionEntity entity) {
        return new ClimateActionDTO(
                entity.getClimateActionId(),
                entity.getTitle(),
                entity.getDescription(),
                entity.getEstimatedCO2Reduction(),
                entity.getPoints(),
                entity.getCategory().getCategoryId()
        );
    }
}

