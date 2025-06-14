package az.turing.service.impl;

import az.turing.domain.entity.Profile;
import az.turing.domain.enums.Status;
import az.turing.domain.repository.ProfileRepo;
import az.turing.dto.request.ProfileRequest;
import az.turing.dto.response.ProfileResponse;
import az.turing.exception.AlreadyDeletedException;
import az.turing.exception.NotFoundException;
import az.turing.mapper.ProfileMapper;
import az.turing.service.ProfileService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    private final ProfileMapper profileMapper;
    private final ProfileRepo profileRepo;

    @Override
    @Transactional
    public ProfileResponse saveProfile(ProfileRequest profileRequest) {
        Profile profile = profileMapper.toEntityFromRequest(profileRequest);
        profile.setStatus(Status.ACTIVE);
        Profile savedProfile = profileRepo.save(profile);
        return profileMapper.toDto(savedProfile);
    }

    @Override
    public ProfileResponse getProfileById(Long id) {
        Profile profile = profileFindById(id);
        return profileMapper.toDto(profile);
    }

    @Override
    public List<ProfileResponse> getAllProfiles() {
        return profileRepo.findAllByStatus(Status.ACTIVE).stream()
                .map(profileMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteProfileById(Long id) {
        Profile profile = profileFindById(id);
        if (profile.getStatus() == Status.DELETED) {
            throw new AlreadyDeletedException("Profile with id: " + id + " already deleted");
        }
        profileRepo.delete(profile);
    }

    @Override
    @Transactional
    public ProfileResponse updateProfile(ProfileRequest profileRequest, Long id) {
        Profile profile = profileFindById(id);
        Profile entity = profileMapper.toEntityFromRequest(profileRequest);
        profile.setUser(entity.getUser());
        profile.setBio(profileRequest.getBio());
        profile.setGender(profileRequest.getGender());
        profile.setLocation(profileRequest.getLocation());
        profile.setPhotoUrl(profileRequest.getPhotoUrl());
        profile.setStatus(Status.ACTIVE);
        Profile savedProfile = profileRepo.save(profile);
        return profileMapper.toDto(savedProfile);
    }

    public Profile profileFindById(Long id) {
        return profileRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Profile with id: " + id + " not found"));
    }

}
