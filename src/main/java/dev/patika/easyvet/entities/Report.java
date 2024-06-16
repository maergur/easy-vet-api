package dev.patika.easyvet.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "reports")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_id", columnDefinition = "serial")
    private Long id;

    @NotNull
    @Column(name = "report_price")
    private Long price;

    @NotNull
    @Column(name = "report_description")
    private String description;

    @OneToOne()
    @JoinColumn(name = "report_appointment_id", referencedColumnName = "appointment_id")
    private Appointment appointment;

    @JsonIgnore
    @OneToMany (mappedBy = "report" ,cascade = CascadeType.REMOVE,fetch = FetchType.LAZY)
    private List<Vaccine> vaccineList;
}
