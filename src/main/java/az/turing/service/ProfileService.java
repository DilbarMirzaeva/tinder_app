package az.turing.service;

import az.turing.dto.request.ProfileRequest;
import az.turing.dto.response.ProfileResponse;

import java.util.List;


public interface ProfileService {
    ProfileResponse saveProfile(ProfileRequest profileRequest);
    ProfileResponse getProfileById(Long id);
    List<ProfileResponse> getAllProfiles();
    void deleteProfileById(Long id);
    ProfileResponse updateProfile(ProfileRequest profileRequest);

}
