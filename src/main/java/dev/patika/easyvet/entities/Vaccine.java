package dev.patika.easyvet.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "vaccines")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vaccine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vaccine_id", columnDefinition = "serial")
    private int id;

    @NotNull
    @Column(name = "vaccine_name")
    private String name;

    @NotNull
    @Column(name = "vaccine_code")
    private String code;

    @Temporal(TemporalType.DATE)
    @NotNull
    @Column(name = "vaccine_protection_sd")
    private LocalDate protectionSD;

    @Temporal(TemporalType.DATE)
    @NotNull
    @Column(name = "vaccine_protection_fd")
    private LocalDate protectionFD;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "animal_id")
    private Animal animal;

}
