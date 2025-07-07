package az.turing.service.impl;

import az.turing.domain.entity.Profile;
import az.turing.domain.entity.User;
import az.turing.domain.enums.Status;
import az.turing.domain.repository.ProfileRepo;
import az.turing.domain.repository.UserRepo;
import az.turing.dto.request.ProfileCreateRequest;
import az.turing.dto.request.ProfileUpdateRequest;
import az.turing.dto.request.StatusUpdateRequest;
import az.turing.dto.request.UserRequest;
import az.turing.dto.response.ProfileResponse;
import az.turing.exception.AlreadyDeletedException;
import az.turing.exception.AlreadyExistsException;
import az.turing.exception.NotFoundException;
import az.turing.mapper.ProfileMapper;
import az.turing.mapper.UserMapper;
import az.turing.service.ProfileService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static az.turing.domain.enums.Status.ACTIVE;
import static az.turing.domain.enums.Status.DELETED;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    private final ProfileMapper profileMapper;
    private final ProfileRepo profileRepo;
    private final UserRepo userRepo;
    private final UserMapper userMapper;

    @Override
    @Transactional
    public ProfileResponse saveProfile(ProfileCreateRequest profileRequest) {
        UserRequest userRequest=profileRequest.getUserRequest();
        Optional<User> userOpt=userRepo.findByUsername(userRequest.getUsername());

        User userEntity;
        if(userOpt.isPresent()){
            userEntity=userOpt.get();
            if(userEntity.getProfile()!=null){
                throw new AlreadyExistsException("User with username " + userRequest.getUsername() + " already have a profile");
            }

            if(!Objects.equals(userEntity.getAge(),userRequest.getAge())){
                userEntity.setAge(userRequest.getAge());
            }

            if (userEntity.getStatus()==DELETED){
                userEntity.setStatus(ACTIVE);
            }
        }else {
            userEntity=userMapper.toEntityFromRequest(userRequest);
            userEntity.setStatus(ACTIVE);
            userRepo.save(userEntity);
        }

        Profile profile = profileMapper.toEntityFromRequest(profileRequest);
        profile.setUser(userEntity);
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
        if (profile.getStatus() == DELETED) {
            throw new AlreadyDeletedException("Profile with id: " + id + " already deleted");
        }
        profile.setStatus(DELETED);
        profile.getUser().setStatus(DELETED);
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

    @Override
    public ProfileResponse updateProfileStatus(StatusUpdateRequest request, Long id) {
        Profile profile = profileFindById(id);
        Status status=request.getStatus();

        profile.setStatus(status);
        profile.getUser().setStatus(status);

        Profile savedProfile = profileRepo.save(profile);
        return profileMapper.toDto(savedProfile);
    }

    public Profile profileFindById(Long id) {
        return profileRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Profile with id: " + id + " not found"));
    }
}
