package com.learningcenter.profiles.interfaces.acl;

import com.learningcenter.profiles.domain.model.commands.CreateProfileCommand;
import com.learningcenter.profiles.domain.model.queries.GetProfileByEmailQuery;
import com.learningcenter.profiles.domain.model.valueobjects.EmailAddress;
import com.learningcenter.profiles.domain.services.ProfileCommandService;
import com.learningcenter.profiles.domain.services.ProfileQueryService;
import org.springframework.stereotype.Service;

/**
 * Service facade for the profile context
 * <p>
 *     It is used by the other contexts to interact with the profile context
 *     it is implemented as part of anti-corruption layer (ACL) to be consumed by other context
 * </p>
 */
@Service
public class ProfileContextFacade {
    private final ProfileCommandService profileCommandService;
    private final ProfileQueryService profileQueryService;


    public ProfileContextFacade(ProfileCommandService profileCommandService, ProfileQueryService profileQueryService) {
        this.profileCommandService = profileCommandService;
        this.profileQueryService = profileQueryService;
    }

    /**
     * Create a new profile
     * @param firstName the first name
     * @param lastName the last name
     * @param email the email
     * @param street the street
     * @param number the number
     * @param city the city
     * @param state the state
     * @param zipCode the zip code
     * @param country the country
     * @return the profile id
     */
    public Long createProfile(String firstName, String lastName, String email, String street, String number,
                              String city, String state, String zipCode, String country){
        var createProfileCommand = new CreateProfileCommand(firstName, lastName, email, street,
                number, city, state, zipCode, country);

        return profileCommandService.handle(createProfileCommand);
    }


    /**
     * Fetch the profile id by email
     * @param email the email
     * @return the profile is
     */
    public Long fetchProfileIdByEmail(String email){
        var getProfileByEmailQuery = new GetProfileByEmailQuery(new EmailAddress(email));
        var profile = profileQueryService.handle(getProfileByEmailQuery);
        if(profile.isEmpty()){
            return  0L;
        }
        return profile.get().getId();
    }
}
