package lk.ijse.cmjd114_115.ecocheck.controller;

import lk.ijse.cmjd114_115.ecocheck.dto.UserActionDTO;
import lk.ijse.cmjd114_115.ecocheck.service.UserActionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/user-action")
@RequiredArgsConstructor
public class UserActionController {
    private final UserActionService userActionService;

    @PostMapping
    ResponseEntity<Void> createUserAction(@RequestBody UserActionDTO userAction) {
        userActionService.createUserAction(userAction);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("{userActionId}")
    ResponseEntity<UserActionDTO> getSelectedUserAction(@PathVariable String userActionId) {
        return new ResponseEntity<>(userActionService.getSelectedUserAction(userActionId), HttpStatus.OK);
    }

    @GetMapping
    ResponseEntity<List<UserActionDTO>> getAllUsers(@RequestParam(required = false) String userId) {
        if (userId != null) {
            return new ResponseEntity<>(userActionService.getUserActionsByUser(userId), HttpStatus.OK);
        }
        return new ResponseEntity<>(userActionService.getAllUserActions(), HttpStatus.OK);
    }

    @PatchMapping("{userActionId}")
    ResponseEntity<Void> updateUserAction(@PathVariable String userActionId, @RequestBody UserActionDTO userAction) {
        userActionService.updateUserAction(userActionId, userAction);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("{userActionId}")
    ResponseEntity<Void> deleteUserAction(@PathVariable String userActionId) {
        userActionService.deleteUserAction(userActionId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

