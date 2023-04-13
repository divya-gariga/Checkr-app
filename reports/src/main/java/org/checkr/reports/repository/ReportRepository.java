package org.checkr.reports.repository;

import org.checkr.reports.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ReportRepository extends JpaRepository<Report, Integer> {


    Optional<Report> findByCandidateId(int candidateId);
}