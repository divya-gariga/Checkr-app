package org.checkr.reports.service;

import org.checkr.reports.entity.Report;

import java.util.Optional;

public interface ReportService {
    Optional<Report> findByCandidateId(int candidateId);
    Optional<Report> updateByCandidateId(int candidateId,Report report);
}
