package com.i2i.sms.model;

import com.i2i.sms.dto.CreateAddressDto;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.UUID;

/**
 * <p>
 * The Address class represents an address entity with attributes such as id, door number,
 * street, city, state, zipcode, mobile number, and student. It includes getter and
 * setter methods for accessing and modifying these attributes.
 * </p>
 */
@Builder
@Getter
@Setter
@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "door_number")
    private String doorNumber;

    @Column(name = "street")
    private String street;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "zipcode")
    private int zipcode;

    @Column(name = "mobile_number")
    private String mobileNumber;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "student_id")
    private Student student;

    public Address(UUID id, String doorNumber,
                   String street, String city,
                   String state, int zipcode,
                   String mobileNumber, Student student) {
        this.id = id;
        this.doorNumber = doorNumber;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
        this.mobileNumber = mobileNumber;
        this.student = student;
    }

    public Address() {
    }
}