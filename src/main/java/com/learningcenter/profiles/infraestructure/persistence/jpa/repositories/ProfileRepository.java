package com.learningcenter.profiles.infraestructure.persistence.jpa.repositories;

import com.learningcenter.profiles.domain.model.aggregates.Profile;
import com.learningcenter.profiles.domain.model.valueobjects.EmailAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<Profile, Long> {

    Optional<Profile> findByEmail(EmailAddress emailAddress);

}
