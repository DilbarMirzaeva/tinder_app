package az.turing.service;

import az.turing.domain.entity.User;
import az.turing.dto.response.MatchResponse;

import java.util.List;

public interface MatchService {
    void createMatch(User fromUser,User toUser);
    List<MatchResponse> getAllMatchesByUser(Long userId);
    boolean isMatched(Long userId1, Long userId2);
    void deleteMatch(Long matchId);
}
