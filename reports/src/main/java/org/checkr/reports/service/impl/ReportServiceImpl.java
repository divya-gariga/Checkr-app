package org.checkr.reports.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.checkr.reports.entity.Report;
import org.checkr.reports.repository.ReportRepository;
import org.checkr.reports.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@Slf4j
public class ReportServiceImpl implements ReportService {
    @Autowired
    private ReportRepository reportRepository;

    @Override
    public Optional<Report> findByCandidateId(int candidateId) {
        return reportRepository.findByCandidateId(candidateId);
    }

    @Override
    public Optional<Report> updateByCandidateId(int candidateId, Report report) {
        Optional<Report> result = reportRepository.findByCandidateId(candidateId);
        if(result.isPresent()) {
            reportRepository.save(report);
        }
        return reportRepository.findByCandidateId(candidateId);
    }
}
