package az.turing.service.impl;

import az.turing.domain.entity.User;
import az.turing.dto.response.LikeResponse;
import az.turing.dto.response.MatchResponse;
import az.turing.service.MatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MatchServiceImpl implements MatchService {

    private final UserServiceImpl userService;
    private final LikeServiceImpl likeService;

    @Override
    public void createMatch(User fromUser, User toUser) {
    }

    @Override
    public List<MatchResponse> getAllMatchesByUser(Long userId) {
        User user=userService.userFindById(userId);

        List<LikeResponse> likesByUser=likeService.getAllLikesByUser(user.getUsername());
        List<LikeResponse> likesReceivedUser=likeService.getAllLikesReceived(user.getUsername());

        Set<String> receivedUsernames=likesReceivedUser.stream()
                .map(like->like.getFromUser().getUsername())
                .collect(Collectors.toSet());

        return likesByUser.stream()
                .filter(like->receivedUsernames.contains(like.getToUser().getUsername()))
                .map(like->MatchResponse.builder()
                        .user1(like.getFromUser())
                        .user2(like.getToUser())
                        .build())
                .toList();
    }

    @Override
    public boolean isMatched(Long userId1, Long userId2) {
        User user1=userService.userFindById(userId1);
        User user2=userService.userFindById(userId2);

        boolean user1LikedUser2=user1.getLikesSent().stream()
                .anyMatch(likedUser->likedUser.getId().equals(userId2));

        boolean user2LikedUser1=user2.getLikesSent().stream()
                .anyMatch(likedUser->likedUser.getId().equals(userId1));

        return user1LikedUser2 && user2LikedUser1;
    }

    @Override
    public void deleteMatch(Long matchId) {

    }
}
