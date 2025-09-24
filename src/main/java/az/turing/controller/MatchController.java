package az.turing.controller;

import az.turing.dto.response.MatchResponse;
import az.turing.service.MatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/matches")
@RequiredArgsConstructor
@Validated
public class MatchController {


    private final MatchService matchService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<MatchResponse>> getAll(@PathVariable Long userId){
        return ResponseEntity.ok(matchService.getAllMatchesByUser(userId));
    }

    @GetMapping("/isMatched")
    public ResponseEntity<Boolean> isMatched(@RequestParam Long userId1,@RequestParam Long userId2){
        return ResponseEntity.ok(matchService.isMatched(userId1,userId2));
    }

    @DeleteMapping("/{matchId}")
    public ResponseEntity<Void> deleteMatch(@PathVariable Long matchId){
        matchService.deleteMatch(matchId);
        return ResponseEntity.noContent().build();
    }


}
