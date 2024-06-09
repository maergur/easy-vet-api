package dev.patika.easyvet.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
    @Table(name = "doctors")
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class Doctor {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "doctor_id", columnDefinition = "serial")
        private Long id;

        @NotNull
        @Column(name = "doctor_name")
        private String name;

        @NotNull
        @Email
        @Column(name = "doctor_mail", unique = true)
        private String mail;

        @NotNull
        @Column(name = "doctor_phone")
        private String phone;

        @NotNull
        @Column(name = "doctor_address")
        private String address;

        @NotNull
        @Column(name = "doctor_city")
        private String city;

        @JsonIgnore
        @OneToMany (mappedBy = "doctor", cascade = CascadeType.REMOVE,fetch = FetchType.LAZY)
        private List<Appointment> appointmentList;

        @JsonIgnore
        @OneToMany (mappedBy = "doctor" ,cascade = CascadeType.REMOVE,fetch = FetchType.LAZY)
        private List<AvailableDate> availableDateList;
    }
