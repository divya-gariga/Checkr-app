package org.checkr.reports.controller;

import lombok.extern.slf4j.Slf4j;
import org.checkr.reports.entity.Report;
import org.checkr.reports.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@Slf4j
@RequestMapping("/api/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/{candidateId}")
    public Optional<Report> findByCandidateId(@PathVariable int candidateId){
        return reportService.findByCandidateId(candidateId);
    }
    @PutMapping("/{candidateId}")
    public Optional<Report> updateUser(@PathVariable int candidateId, @RequestBody Report report){
        log.trace("request received at updateUser");
        return reportService.updateByCandidateId(candidateId,report);
    }
}
