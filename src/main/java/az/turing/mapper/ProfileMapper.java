package az.turing.mapper;

import az.turing.domain.entity.Profile;
import az.turing.dto.request.ProfileRequest;
import az.turing.dto.response.ProfileResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",uses = {UserMapper.class})
public interface ProfileMapper extends EntityMapper<Profile, ProfileResponse, ProfileRequest> {
    @Override
    Profile toEntityFromResponse(ProfileResponse profileResponse);

    @Override
    ProfileResponse toDto(Profile profile);

    ProfileRequest toRequest(Profile profile);
    Profile toEntityFromRequest(ProfileRequest profileRequest);
}
