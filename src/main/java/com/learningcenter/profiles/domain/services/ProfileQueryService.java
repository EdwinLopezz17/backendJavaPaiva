package com.learningcenter.profiles.domain.services;

import com.learningcenter.profiles.domain.model.aggregates.Profile;
import com.learningcenter.profiles.domain.model.queries.GetProfileByEmailQuery;
import com.learningcenter.profiles.domain.model.queries.GetProfileByIdQuery;

import java.util.Optional;

public interface ProfileQueryService {
    Optional<Profile> handle(GetProfileByEmailQuery query);

    Optional<Profile> handle(GetProfileByIdQuery query);

}
