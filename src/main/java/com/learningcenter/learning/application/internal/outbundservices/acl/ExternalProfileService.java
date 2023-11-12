package com.learningcenter.learning.application.internal.outbundservices.acl;

import com.learningcenter.learning.domain.model.valueobjects.ProfileId;
import com.learningcenter.profiles.interfaces.acl.ProfileContextFacade;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * This class is outbound services used by the learning Context to interact with the Profile
 * It is implemented as part of on anti corruption layer (ACL) to decouple the learning context from
 * the profile context
 */
@Service

public class ExternalProfileService {

    private final ProfileContextFacade profileContextFacade;

    public ExternalProfileService(ProfileContextFacade profileContextFacade) {
        this.profileContextFacade = profileContextFacade;
    }

    /**
     * Fetch profileId by email
     * @param email the email to search for
     * @return profileId found, empty otherwise
     */
    public Optional<ProfileId> fetchProfileIdByEmail(String email){
        var profileId = profileContextFacade.fetchProfileIdByEmail(email);
        if(profileId == 0L) return  Optional.empty();

        return Optional.of(new ProfileId(profileId));
    }


    /**
     *
     * @param firstName
     * @param lastName
     * @param email
     * @param street
     * @param number
     * @param city
     * @param state
     * @param zipcode
     * @param country
     * @return
     */
    public Optional<ProfileId> createProfileId(String firstName, String lastName, String email, String street,
                                               String number, String city, String state, String zipcode,
                                               String country){
        var profileId = profileContextFacade.createProfile(
                firstName, lastName, email, street, number, city, state, zipcode, country);
        if(profileId == 0L) return Optional.empty();
        return Optional.of(new ProfileId(profileId));
    }
}
