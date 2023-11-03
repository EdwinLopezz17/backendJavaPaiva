package com.learningcenter.profiles.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record StreetAddress(
        String street,
        String number,
        String city,
        String zipcode,
        String country
) {
    public StreetAddress(){
        this(null, null, null, null, null);
    }

    public StreetAddress{
        if(street == null || street.isBlank()){
            throw new IllegalArgumentException("Street cannot be null or blank");
        }
        if(number == null || number.isBlank()){
            throw new IllegalArgumentException("Number cannot be null or blank");
        }
        if(city == null || city.isBlank()){
            throw new IllegalArgumentException("City cannot be null or blank");
        }
        if(zipcode == null || zipcode.isBlank()){
            throw new IllegalArgumentException("Zipcode cannot be null or blank");
        }
        if(country == null || country.isBlank()){
            throw new IllegalArgumentException("Country cannot be null or blank");
        }
    }
    public StreetAddress(String street, String city, String zipcode, String country){
        this(street,null, city, zipcode, country);
    }

    public String getStreetAddress(){
        return street+' '+number;
    }
}
