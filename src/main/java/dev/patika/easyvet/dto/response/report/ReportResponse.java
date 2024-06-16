package dev.patika.easyvet.dto.response.report;

import dev.patika.easyvet.entities.Appointment;
import dev.patika.easyvet.entities.Vaccine;

import java.util.List;

public class ReportResponse {
    private Long price;
    private String description;
    private Appointment appointment;
    private List<Vaccine> vaccineList;
}
