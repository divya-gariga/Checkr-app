package org.checkr.reports.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.checkr.reports.entity.Report;
import org.checkr.reports.exception.ReportNotFoundException;
import org.checkr.reports.repository.ReportRepository;
import org.checkr.reports.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.Optional;
import java.util.Set;


@Service
@Slf4j
public class ReportServiceImpl implements ReportService {
    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private Validator validator;

    @Override
    public Optional<Report> findByCandidateId(int candidateId) {
        Optional<Report> reports=reportRepository.findByCandidateId(candidateId);
        if(reports.isPresent()){
            return reportRepository.findByCandidateId(candidateId);
        }
        else {
            throw new ReportNotFoundException("Report not found with the given candidateId");
        }
    }

    @Override
    public Optional<Report> updateByCandidateId(int candidateId, Report report) {
        Optional<Report> result = reportRepository.findByCandidateId(candidateId);
        Set<ConstraintViolation<Report>> violations = validator.validate(report);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
        if(result.isPresent()) {
            reportRepository.save(report);
            return reportRepository.findByCandidateId(candidateId);
        }
        else{
            throw new ReportNotFoundException("Report not found with the given candidateId");
        }
    }
}
