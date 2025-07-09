package az.turing.service.impl;

import az.turing.domain.entity.User;
import az.turing.dto.response.MatchResponse;
import az.turing.service.MatchService;

import java.util.List;

public class MatchServiceImpl implements MatchService {
    @Override
    public void createMatch(User fromUser, User toUser) {

    }

    @Override
    public List<MatchResponse> getAllMatchesByUser(Long userId) {
        return List.of();
    }

    @Override
    public boolean isMatched(Long userId1, Long userId2) {
        return false;
    }

    @Override
    public void deleteMatch(Long matchId) {

    }
}
