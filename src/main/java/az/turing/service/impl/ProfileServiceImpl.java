package az.turing.service.impl;

import az.turing.domain.entity.Profile;
import az.turing.domain.entity.User;
import az.turing.domain.enums.Status;
import az.turing.domain.repository.ProfileRepo;
import az.turing.domain.repository.UserRepo;
import az.turing.dto.request.ProfileCreateRequest;
import az.turing.dto.request.ProfileUpdateRequest;
import az.turing.dto.response.ProfileResponse;
import az.turing.exception.AlreadyDeletedException;
import az.turing.exception.AlreadyExistsException;
import az.turing.exception.NotFoundException;
import az.turing.mapper.ProfileMapper;
import az.turing.mapper.UserMapper;
import az.turing.service.ProfileService;
import az.turing.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    private final ProfileMapper profileMapper;
    private final ProfileRepo profileRepo;
    private final UserService userService;
    private final UserRepo userRepo;
    private final UserMapper userMapper;

    @Override
    @Transactional
    public ProfileResponse saveProfile(ProfileCreateRequest profileRequest) {
        User user=userService.saveAndReturnUser(profileRequest.getUserRequest());
        Profile profile = profileMapper.toEntityFromRequest(profileRequest);
        profile.setUser(user);
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
                .toList();
    }

    @Override
    @Transactional
    public void deleteProfileById(Long id) {
        Profile profile = profileFindById(id);
        if (profile.getStatus() == Status.DELETED) {
            throw new AlreadyDeletedException("Profile with id: " + id + " already deleted");
        }
        profile.setStatus(Status.DELETED);
        profile.getUser().setStatus(Status.DELETED);
        profileRepo.save(profile);
    }

    @Override
    @Transactional
    public ProfileResponse updateProfile(ProfileUpdateRequest profileRequest, Long id) {
        Profile profile = profileFindById(id);

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
