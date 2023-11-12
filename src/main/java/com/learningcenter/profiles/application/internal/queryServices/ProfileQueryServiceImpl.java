package com.learningcenter.profiles.application.internal.queryServices;

import com.learningcenter.profiles.domain.model.aggregates.Profile;
import com.learningcenter.profiles.domain.model.queries.GetProfileByEmailQuery;
import com.learningcenter.profiles.domain.model.queries.GetProfileByIdQuery;
import com.learningcenter.profiles.domain.services.ProfileQueryService;
import com.learningcenter.profiles.infraestructure.persistence.jpa.repositories.ProfileRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfileQueryServiceImpl implements ProfileQueryService {

    private final ProfileRepository profileRepository;

    public ProfileQueryServiceImpl(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }


    /**
     * @param query is the object GetProfileByEmailQuery
     * @return Profile or null
     */
    @Override
    public Optional<Profile> handle(GetProfileByEmailQuery query) {
        return profileRepository.findByEmail(query.emailAddress());
    }

    /**
     * @param query s the object GetProfileByIdQuery
     * @return Profile or null
     */
    @Override
    public Optional<Profile> handle(GetProfileByIdQuery query) {
        return profileRepository.findById(query.profileId());
    }
}
