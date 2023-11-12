package com.learningcenter.profiles.interfaces.rest.transform;


import com.learningcenter.profiles.domain.model.commands.CreateProfileCommand;
import com.learningcenter.profiles.interfaces.rest.resource.CreateProfileResource;

/**
 * CreateProfileCommandFromResourceAssembler
 * <p>
 *     Assembler to create a {@link com.learningcenter.profiles.domain.model.commands.CreateProfileCommand}
 *     from a {@link  CreateProfileResource}
 *     <b>
 *         this class applies the assembler pattern to transform a CreateProfileResource resource into
 *         a CreateProfileCommand
 *     </b>
 * </p>
 */
public class CreateProfileCommandFromResourceAssembler {

    /**
     * toCommandFromResource
     * @param resource {@link CreateProfileResource} the resource to transform
     * @return {@link CreateProfileCommand} command the resulting command
     */
    public static CreateProfileCommand toCommandFromResource(CreateProfileResource resource){
        return new CreateProfileCommand(resource.firstName(), resource.lastName(), resource.email(), resource.street(), resource.number(),
                resource.city(), resource.state(), resource.zipCode(), resource.country());
    }
}
