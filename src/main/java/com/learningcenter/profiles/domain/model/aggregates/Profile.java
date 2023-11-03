package com.learningcenter.profiles.domain.model.aggregates;


import com.learningcenter.profiles.domain.model.valueobjects.PersonName;
import com.learningcenter.profiles.domain.model.valueobjects.StreetAddress;
import com.learningcenter.profiles.domain.model.valueobjects.EmailAddress;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.AbstractAggregateRoot;

import java.util.Date;

@Entity
public class Profile extends AbstractAggregateRoot<Profile> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private PersonName name;

    @Embedded
    private EmailAddress emailAddress;

    @Embedded
    private StreetAddress address;

    @CreatedDate
    private Date createdAt;

    @LastModifiedDate
    private Date updatedAt;

    public Profile(String firstName, String lastName, String email, String streetAddress, String city, String state, String zipcode){
        this.name = new PersonName(firstName, lastName);
        this.emailAddress = new EmailAddress(email);
        this.address = new StreetAddress(streetAddress, city, state, zipcode);
    }

    public Profile(){

    }
    public void updateName(String firstName, String lastName){
        this.name = new PersonName(firstName, lastName);
    }

    public void updatedAddress(String streetAddress, String city, String state, String zipcode){
        this.address = new StreetAddress(streetAddress, city, state, zipcode);
    }
    public String getFullName(){
        return this.name.getFullName();
    }
    public String getStreetAddress(){
        return this.address.getStreetAddress();
    }
    public Long getId(){
        return this.id;
    }
}
