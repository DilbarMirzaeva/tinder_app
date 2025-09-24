package az.turing.service.impl;

import az.turing.domain.entity.Match;
import az.turing.domain.entity.User;
import az.turing.domain.repository.MatchRepo;
import az.turing.dto.response.MatchResponse;
import az.turing.exception.NotFoundException;
import az.turing.mapper.MatchMapper;
import az.turing.service.MatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MatchServiceImpl implements MatchService {

    private final UserServiceImpl userService;
//    private final LikeServiceImpl likeService;
    private final MatchRepo matchRepo;
    private final MatchMapper matchMapper;

    @Override
    public List<MatchResponse> getAllMatchesByUser(Long userId) {
        User user=findUserById(userId);
        List<Match> matches=matchRepo.findByUser1OrUser2(user,user);
        return matches.stream().map(matchMapper::toDto).collect(Collectors.toList());

//        List<LikeResponse> likesByUser=likeService.getAllLikesByUser(user.getUsername());
//        List<LikeResponse> likesReceivedUser=likeService.getAllLikesReceived(user.getUsername());
//
//        Set<String> receivedUsernames=likesReceivedUser.stream()
//                .map(like->like.getFromUser().getUsername())
//                .collect(Collectors.toSet());
//
//        return likesByUser.stream()
//                .filter(like->receivedUsernames.contains(like.getToUser().getUsername()))
//                .map(like->MatchResponse.builder()
//                        .user1(like.getFromUser())
//                        .user2(like.getToUser())
//                        .build())
//                .toList();
    }

    @Override
    public boolean isMatched(Long userId1, Long userId2) {
        User user1=findUserById(userId1);
        User user2=findUserById(userId2);

//        boolean user1LikedUser2=user1.getLikesSent().stream()
//                .anyMatch(likedUser->likedUser.getId().equals(userId2));
//
//        boolean user2LikedUser1=user2.getLikesSent().stream()
//                .anyMatch(likedUser->likedUser.getId().equals(userId1));

        return matchRepo.existsByUser1AndUser2OrUser2AndUser1(user1,user2,user2,user1);
    }

    @Override
    public void deleteMatch(Long matchId) {
        matchRepo.findById(matchId)
                        .orElseThrow(()->new NotFoundException("Matching not found"));
        matchRepo.deleteById(matchId);
    }

    public User findUserById(Long userId) {
        return userService.userFindById(userId);
    }
}
