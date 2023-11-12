package com.learningcenter.profiles.domain.services;

import com.learningcenter.profiles.domain.model.commands.CreateProfileCommand;

public interface ProfileCommandService {

    Long handle(CreateProfileCommand command);
}
