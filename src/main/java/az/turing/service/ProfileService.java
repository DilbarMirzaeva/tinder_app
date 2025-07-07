package az.turing.service;

import az.turing.dto.request.ProfileCreateRequest;
import az.turing.dto.request.ProfileUpdateRequest;
import az.turing.dto.request.StatusUpdateRequest;
import az.turing.dto.response.ProfileResponse;

import java.util.List;


public interface ProfileService {
    ProfileResponse saveProfile(ProfileCreateRequest profileRequest);
    ProfileResponse getProfileById(Long id);
    List<ProfileResponse> getAllProfiles();
    void deleteProfileById(Long id);
    ProfileResponse updateProfile(ProfileUpdateRequest profileRequest, Long id);
    ProfileResponse updateProfileStatus(StatusUpdateRequest request, Long id);
}
