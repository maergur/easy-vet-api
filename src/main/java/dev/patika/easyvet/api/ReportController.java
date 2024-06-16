package dev.patika.easyvet.api;


import dev.patika.easyvet.business.abstracts.IReportService;
import dev.patika.easyvet.core.config.modelMapper.IModelMapperService;
import dev.patika.easyvet.entities.AvailableDate;
import dev.patika.easyvet.entities.Report;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping ("v1/reports") public class ReportController {
    private final IReportService reportService;
    private IModelMapperService modelMapper;

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Report save(@RequestBody Report report)  {
        return this.reportService.save(report);
    }
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public Report update (@RequestBody Report report) {
        return this.reportService.update(report);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    void delete (@PathVariable("id") Long id){
        this.reportService.delete(id);
    }


    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<Report> getAllReports() {
        return this.reportService.getAll();
    }

}

