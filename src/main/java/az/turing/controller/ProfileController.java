package az.turing.controller;

import az.turing.dto.request.ProfileRequest;
import az.turing.dto.response.ProfileResponse;
import az.turing.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    @PostMapping
    public ResponseEntity<ProfileResponse> save(@RequestBody ProfileRequest profileRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(profileService.saveProfile(profileRequest));
    }

    @PutMapping
    public ResponseEntity<ProfileResponse> update(@RequestBody ProfileRequest profileRequest,@RequestParam Long id) {
        return ResponseEntity.ok(profileService.updateProfile(profileRequest, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@RequestParam Long id) {
        profileService.deleteProfileById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProfileResponse>> getAll() {
        return ResponseEntity.ok(profileService.getAllProfiles());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfileResponse> getProfile(@PathVariable Long id) {
        return ResponseEntity.ok(profileService.getProfileById(id));
    }

}
