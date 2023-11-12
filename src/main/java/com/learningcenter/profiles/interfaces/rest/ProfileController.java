package com.learningcenter.profiles.interfaces.rest;

import com.learningcenter.profiles.domain.model.queries.GetProfileByIdQuery;
import com.learningcenter.profiles.domain.services.ProfileCommandService;
import com.learningcenter.profiles.domain.services.ProfileQueryService;
import com.learningcenter.profiles.interfaces.rest.resource.CreateProfileResource;
import com.learningcenter.profiles.interfaces.rest.resource.ProfileResource;
import com.learningcenter.profiles.interfaces.rest.transform.CreateProfileCommandFromResourceAssembler;
import com.learningcenter.profiles.interfaces.rest.transform.ProfileResourceFromEntityAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/profile", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProfileController {

    private final ProfileQueryService profileQueryService;
    private final ProfileCommandService profileCommandService;


    public ProfileController(ProfileQueryService profileQueryService, ProfileCommandService profileCommandService) {
        this.profileQueryService = profileQueryService;
        this.profileCommandService = profileCommandService;
    }


    /**
     * POST /api/v1/profile
     * <p> Endpoint that creates a profile</p>
     * @param resource the resource with the information to created the profile
     * @return the created profile
     * @see CreateProfileResource
     * @See ProfileResource
     */
    @PostMapping
    public ResponseEntity<ProfileResource> createProfile(CreateProfileResource resource){
        var createProfileCommand = CreateProfileCommandFromResourceAssembler.toCommandFromResource(resource);
        var profileId = profileCommandService.handle(createProfileCommand);
        if(profileId == 0l){
            return ResponseEntity.badRequest().build();
        }
        var getProfileByIdQuery = new GetProfileByIdQuery(profileId);
        var profile = profileQueryService.handle(getProfileByIdQuery);

        if(profile.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        var profileResource = ProfileResourceFromEntityAssembler.toResourceFromEntity(profile.get());
        return  new ResponseEntity<>(profileResource, HttpStatus.CREATED);
    }

    @GetMapping("/{profileId}")
    public ResponseEntity<ProfileResource> getProfileById(@PathVariable Long profileId){
        var getProfileByIdQuery = new GetProfileByIdQuery(profileId);
        var profile = profileQueryService.handle(getProfileByIdQuery);

        if(profile.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        var profileResource = ProfileResourceFromEntityAssembler.toResourceFromEntity(profile.get());
        return ResponseEntity.ok(profileResource);
    }

}
