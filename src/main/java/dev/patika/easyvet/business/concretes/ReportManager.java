package dev.patika.easyvet.business.concretes;

import dev.patika.easyvet.business.abstracts.IReportService;
import dev.patika.easyvet.core.exception.NotFoundException;
import dev.patika.easyvet.core.utilities.Msg;
import dev.patika.easyvet.dao.IReportRepo;
import dev.patika.easyvet.entities.Animal;
import dev.patika.easyvet.entities.Report;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportManager implements IReportService {

    private final IReportRepo reportRepo;

    @Override
    public Report save(Report report) {
        return this.reportRepo.save(report);
    }

    @Override
    public Report update(Report report) {

        Report checkReport = getById((report.getId()));
        if (checkReport != null) {
            return this.reportRepo.save(report);
        } else {
            throw new RuntimeException(Msg.NOT_FOUND);
        }
    }

    @Override
    public Report getById(Long id) {
        return this.reportRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
    }

    @Override
    public List<Report> getAll() {
        return this.reportRepo.findAll();
    }

    @Override
    public boolean delete(Long id) {
        Report report = this.getById(id);
        this.reportRepo.delete(report);
        return true;
    }
}
