package org.checkr.reports.controller;

import org.checkr.reports.entity.Report;
import org.checkr.reports.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/reports")
@Validated
public class ReportController {
    @Autowired
    private ReportService reportService;

    @GetMapping("/{candidateId}")
    public Optional<Report> findByCandidateId(@Valid @PathVariable int candidateId){
        return reportService.findByCandidateId(candidateId);
    }
    @PutMapping("/{candidateId}")
    public Optional<Report> updateUser(@Valid @PathVariable int candidateId, @Valid @RequestBody Report report){
        return reportService.updateByCandidateId(candidateId,report);
    }

}
