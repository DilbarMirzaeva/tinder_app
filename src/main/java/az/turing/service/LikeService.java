package az.turing.service;

import az.turing.dto.request.LikeRequest;
import az.turing.dto.response.LikeResponse;

import java.util.List;

public interface LikeService {
    LikeResponse like(LikeRequest request);

    List<LikeResponse> getAllLikesByUser(String username);

    List<LikeResponse> getAllLikesReceived(String username);

    void deleteLike(Long id);
}
