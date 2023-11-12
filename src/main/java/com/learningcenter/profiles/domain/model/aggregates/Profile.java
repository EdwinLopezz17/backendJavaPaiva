package com.learningcenter.profiles.domain.model.aggregates;


import com.learningcenter.profiles.domain.model.valueobjects.PersonName;
import com.learningcenter.profiles.domain.model.valueobjects.StreetAddress;
import com.learningcenter.profiles.domain.model.valueobjects.EmailAddress;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@EntityListeners(AuditingEntityListener.class)
@Entity
@Getter
@Setter
public class Profile extends AbstractAggregateRoot<Profile> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private PersonName name;

    @Embedded
    private EmailAddress email;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="street", column = @Column(name="address_street")),
            @AttributeOverride(name = "number", column = @Column(name="address_number")),
            @AttributeOverride(name="city", column = @Column(name="address_city")),
            @AttributeOverride(name="state", column = @Column(name="address_state")),
            @AttributeOverride(name="zipCode", column = @Column(name="address_zip_code")),
            @AttributeOverride(name="country", column = @Column(name="address_country")),
    })
    private StreetAddress address;

    @CreatedDate
    private Date createdAt;

    @LastModifiedDate
    private Date updatedAt;

    public Profile(String firstName, String lastName, String email, String streetAddress, String number,
                   String city, String state, String zipcode, String country){
        this.name = new PersonName(firstName, lastName);
        this.email = new EmailAddress(email);
        this.address = new StreetAddress(streetAddress,number, city, state, zipcode, country);
    }

    public Profile(){

    }
    public void updateName(String firstName, String lastName){
        this.name = new PersonName(firstName, lastName);
    }

    public void updatedAddress(String streetAddress,String number, String city, String state, String zipcode, String country){
        this.address = new StreetAddress(streetAddress, number, city, state, zipcode, country);
    }
    public String getFullName(){
        return this.name.getFullName();
    }
    public String getStreetAddress(){
        return this.address.getStreetAddress();
    }

    public String getEmailAddress(){
        return this.email.address();
    }
    public Long getId(){
        return this.id;
    }
}
