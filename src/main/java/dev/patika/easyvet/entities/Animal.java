package dev.patika.easyvet.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.catalina.util.CustomObjectInputStream;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "animals")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "animal_id")
    private int id;

    @NotNull
    @Column(name = "animal_name")
    private String name;

    @NotNull
    @Column(name = "animal_species")
    private String species;

    @NotNull
    @Column(name = "animal_breed")
    private String breed;

    @NotNull
    @Column(name = "animal_gender")
    private String gender;

    @NotNull
    @Column(name = "animal_color")
    private String color;

    @NotNull
    @Column(name = "animal_dob")
    @Temporal(TemporalType.DATE)
    private LocalDate dateOfBirth;

    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn(name = "animal_customer_id" ,referencedColumnName = "customer_id")
    private Customer customer;

    @JsonIgnore
    @OneToMany(mappedBy = "animal",cascade = CascadeType.REMOVE,fetch = FetchType.EAGER)
    private List<Vaccine> vaccineList;

    @JsonIgnore
    @OneToMany(mappedBy = "animal",cascade = CascadeType.REMOVE,fetch = FetchType.LAZY)
    private List<Appointment> appointmentList;

}
