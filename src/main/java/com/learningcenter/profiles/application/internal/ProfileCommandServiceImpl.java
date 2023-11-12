package com.learningcenter.profiles.application.internal;

import com.learningcenter.profiles.domain.model.aggregates.Profile;
import com.learningcenter.profiles.domain.model.commands.CreateProfileCommand;
import com.learningcenter.profiles.domain.model.valueobjects.EmailAddress;
import com.learningcenter.profiles.domain.services.ProfileCommandService;
import com.learningcenter.profiles.infraestructure.persistence.jpa.repositories.ProfileRepository;

/**
 * ProfileCommandServiceImpl
 *
 * <p>Service that handles the commands for profile</p>
 */
public class ProfileCommandServiceImpl implements ProfileCommandService {

    private  final ProfileRepository profileRepository;

    public ProfileCommandServiceImpl(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Override
    public Long handle(CreateProfileCommand command) {
        var emailAddress = new EmailAddress(command.email());
        profileRepository.findByEmail(emailAddress).map(profile -> {
            throw new IllegalArgumentException("Profile with email "+ command.email()+" already exists");
        });
        var profile = new Profile(command.firstName(), command.lastName(), command.email(), command.street(),
                command.number(), command.city(), command.state(), command.zipCode());

        profileRepository.save(profile);
        return profile.getId();
    }


}
