package com.yapstone.directory.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Contact {

    @Id
    @GeneratedValue
    private Long id;

    @JsonProperty("first-name")
    private String firstName;

    @JsonProperty("last-name")
    private String lastName;

    @JsonProperty("contact-number")
    private String contactNumber;

    @JsonProperty("address")
    private String address;

    @JsonProperty("email-id")
    private String emailId;

}
