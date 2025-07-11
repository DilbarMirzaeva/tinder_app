package az.turing.mapper;

import az.turing.domain.entity.Like;
import az.turing.dto.request.LikeRequest;
import az.turing.dto.response.LikeResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LikeMapper extends EntityMapper<Like, LikeResponse, LikeRequest> {
    @Mapping(target = "likeSent",ignore = true)
    @Mapping(target = "likeReceived",ignore = true)
    LikeResponse toDto(Like like);
    Like toEntityFromResponse(LikeResponse likeResponse);
}
