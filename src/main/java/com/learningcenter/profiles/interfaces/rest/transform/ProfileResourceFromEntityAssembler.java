package com.learningcenter.profiles.interfaces.rest.transform;

import com.learningcenter.profiles.domain.model.aggregates.Profile;
import com.learningcenter.profiles.interfaces.rest.resource.ProfileResource;

public class ProfileResourceFromEntityAssembler {

    public static ProfileResource toResourceFromEntity(Profile profile){
        return new ProfileResource(profile.getId(), profile.getFullName(), profile.getStreetAddress());
    }
}
