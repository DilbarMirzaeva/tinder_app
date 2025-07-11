package az.turing.controller;

import az.turing.dto.request.LikeRequest;
import az.turing.dto.response.LikeResponse;
import az.turing.service.LikeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/likes")
@RequiredArgsConstructor
@Validated
public class LikeController {
    private final LikeService likeService;

    @PostMapping("/save")
    public ResponseEntity<LikeResponse> like(@Valid @RequestBody LikeRequest likeRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(likeService.like(likeRequest));
    }

    @GetMapping("/from")
    public ResponseEntity<List<LikeResponse>> getAllLikesFromUser(@RequestParam String username) {
        return ResponseEntity.ok(likeService.getAllLikesByUser(username));
    }

    @GetMapping("/to")
    public ResponseEntity<List<LikeResponse>> getAllLikesToUser(@RequestParam String username) {
        return ResponseEntity.ok(likeService.getAllLikesReceived(username));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLike(@PathVariable Long id) {
        likeService.deleteLike(id);
        return ResponseEntity.noContent().build();
    }

}
