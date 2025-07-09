package az.turing.service.impl;

import az.turing.dto.request.LikeRequest;
import az.turing.dto.response.LikeResponse;
import az.turing.service.LikeService;

import java.util.List;

public class LikeServiceImpl implements LikeService {
    @Override
    public LikeResponse like(LikeRequest request) {
        return null;
    }

    @Override
    public List<LikeResponse> getAllLikesByUser(String username) {
        return List.of();
    }

    @Override
    public List<LikeResponse> getAllLikesReceived(String username) {
        return List.of();
    }

    @Override
    public void deleteLike(String username) {

    }

    @Override
    public void deleteLike(Long id) {

    }
}
