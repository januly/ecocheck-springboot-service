package lk.ijse.cmjd114_115.ecocheck.service;

import lk.ijse.cmjd114_115.ecocheck.dto.ClimateActionDTO;

import java.util.List;

public interface ClimateActionService {
    void createClimateAction(ClimateActionDTO climateActionDTO);

    ClimateActionDTO getSelectedClimateAction(String climateActionId);

    List<ClimateActionDTO> getClimateActions();

    void updateClimateActions(String climateActionId, ClimateActionDTO climateActionDTO);

    void deleteClimateActions(String climateActionId);
}

