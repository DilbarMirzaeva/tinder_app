package az.turing.service.impl;

import az.turing.domain.entity.Like;
import az.turing.domain.entity.User;
import az.turing.domain.repository.LikeRepo;
import az.turing.dto.request.LikeRequest;
import az.turing.dto.response.LikeResponse;
import az.turing.exception.NotFoundException;
import az.turing.mapper.LikeMapper;
import az.turing.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {

    private final LikeRepo likeRepo;
    private final UserServiceImpl userService;
    private final LikeMapper likeMapper;

    @Override
    public LikeResponse like(LikeRequest request) {
        User toUser=findUser(request.getLikedUsername());
        User fromUser=findUser(request.getFromUsername());

        Like like=Like.builder()
                .fromUser(fromUser)
                .toUser(toUser)
                .createdAt(LocalDateTime.now())
                .build();

        Like savedLike=likeRepo.save(like);
        return likeMapper.toDto(savedLike);
    }

    @Override
    public List<LikeResponse> getAllLikesByUser(String username) {
        User user=findUser(username);
        List<Like> likes= user.getLikesSent();
        return likes.stream()
                .map(likeMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<LikeResponse> getAllLikesReceived(String username) {
        User user=findUser(username);
        List<Like> likes= user.getLikesReceived();
        return likes.stream()
                .map(likeMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteLike(Long id) {
        Like like=likeRepo.findById(id)
                .orElseThrow(()->new NotFoundException("Like not found"));
        likeRepo.delete(like);
    }

    public User findUser(String username) {
        return userService.userFindByUsername(username);
    }
}
