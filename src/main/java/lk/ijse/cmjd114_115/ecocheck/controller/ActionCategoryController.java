package lk.ijse.cmjd114_115.ecocheck.controller;

import lk.ijse.cmjd114_115.ecocheck.dto.ActionCategoryDTO;
import lk.ijse.cmjd114_115.ecocheck.service.ActionCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("api/v1/action-category")
@RestController
@RequiredArgsConstructor
public class ActionCategoryController {
    private final ActionCategoryService actionCategoryService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createAction(@RequestBody ActionCategoryDTO actionCategoryDTO) {
        actionCategoryService.saveActionCategory(actionCategoryDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "{actionCategoryId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ActionCategoryDTO> getSelectedUser(@PathVariable String actionCategoryId) {
        return new ResponseEntity<>(actionCategoryService.getSelectedActionCategory(actionCategoryId), HttpStatus.OK);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ActionCategoryDTO>> getUsers() {
        return new ResponseEntity<>(actionCategoryService.getAllActionCategories(), HttpStatus.OK);
    }

    @PatchMapping(value = "{actionId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateActionCategory(@PathVariable String actionId, @RequestBody ActionCategoryDTO actionCategoryDTO) {
        actionCategoryService.updateActionCategory(actionId, actionCategoryDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "{actionId}")
    public ResponseEntity<Void> deleteActionCategory(@PathVariable String actionId) {
        actionCategoryService.deleteActionCategory(actionId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

