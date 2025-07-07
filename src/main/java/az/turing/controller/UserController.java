package az.turing.controller;

import az.turing.dto.request.StatusUpdateRequest;
import az.turing.dto.request.UserRequest;
import az.turing.dto.response.UserResponse;
import az.turing.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@Validated
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponse> save(@Valid @RequestBody UserRequest userRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveUser(userRequest));
    }

    @PutMapping("/update")
    public ResponseEntity<UserResponse> update(@Valid @RequestBody UserRequest userRequest,@RequestParam Long id) {
        return ResponseEntity.ok(userService.updateUser(userRequest, id));
    }

    @PatchMapping("/status/{id}")
    public ResponseEntity<UserResponse> updateStatus(@Valid @RequestBody StatusUpdateRequest request,@PathVariable Long id){
        return ResponseEntity.ok(userService.updateUserStatus(request, id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserResponse>> getAll() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> get(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }
}
