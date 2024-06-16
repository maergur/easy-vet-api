package dev.patika.easyvet.business.abstracts;
import dev.patika.easyvet.entities.Doctor;
import dev.patika.easyvet.entities.Report;
import java.util.List;

public interface IReportService {
    Report save(Report report);

    Report update(Report report);


    Report getById(Long id);

    List<Report> getAll();

    boolean delete(Long id);
}