package az.turing.mapper;

import az.turing.domain.entity.Profile;
import az.turing.dto.request.ProfileCreateRequest;
import az.turing.dto.response.ProfileResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",uses = {UserMapper.class})
public interface ProfileMapper extends EntityMapper<Profile, ProfileResponse, ProfileCreateRequest> {
    @Override
    Profile toEntityFromResponse(ProfileResponse profileResponse);

    @Override
    ProfileResponse toDto(Profile profile);

    @Mapping(source = "user",target = "userRequest")
    ProfileCreateRequest toRequest(Profile profile);

    @Mapping(source = "userRequest",target = "user")
    Profile toEntityFromRequest(ProfileCreateRequest profileRequest);
}
