package com.learningcenter.profiles.domain.model.queries;

import com.learningcenter.profiles.domain.model.valueobjects.EmailAddress;

public record GetProfileByEmailQuery(EmailAddress emailAddress) {

}
