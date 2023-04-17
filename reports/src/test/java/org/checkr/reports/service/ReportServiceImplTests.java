package org.checkr.reports.service;

import org.checkr.reports.entity.Report;
import org.checkr.reports.exception.ReportNotFoundException;
import org.checkr.reports.repository.ReportRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
class ReportServiceImplTests {

    @Autowired
    private ReportService reportService;

    @MockBean
    private ReportRepository reportRepository;

    private Report report;
    @BeforeEach
    void setup(){
        report = new Report();
        report.setId(1);
        report.setStatus(Report.Status.CLEAR);
        report.setAdjudication(Report.Adjudication.ENGAGED);
        report.setReportPackage("report_package_name");
        report.setCompletedDate(LocalDateTime.now());
        report.setTurnAroundTime("2 days");
        report.setCandidateId(1);
    }

    @Test
    void findByCandidateId() {
        int id=1;
        when(reportRepository.findByCandidateId(id)).thenReturn(Optional.of(report));
        assertEquals(Optional.of(report),reportService.findByCandidateId(id));
    }

    @Test
    void findByCandidateIdWithException() {
        int id=1;
        when(reportRepository.findByCandidateId(id)).thenReturn(Optional.empty());
        Throwable exception= assertThrows(ReportNotFoundException.class,()->reportService.findByCandidateId(id));
        assertEquals("Report not found with the given candidateId",exception.getMessage());
    }

    @Test
    void updateByCandidateId(){
        int id=1;
        when(reportRepository.findByCandidateId(id)).thenReturn(Optional.of(report));
        assertEquals(Optional.of(report),reportService.updateByCandidateId(id,report));
    }

    @Test
    void updateByCandidateIdWithException(){
            int id=1;
            when(reportRepository.findByCandidateId(id)).thenReturn(Optional.empty());
            Throwable exception= assertThrows(ReportNotFoundException.class,()->reportService.updateByCandidateId(id,report));
            assertEquals("Report not found with the given candidateId",exception.getMessage());
    }
    @Test
    void updateByCandidateIdWithValidationError(){
        int id=1;
        when(reportRepository.findByCandidateId(id)).thenReturn(Optional.of(report));
        Report report1 = new Report();
        report1.setId(1);
        report1.setStatus(Report.Status.CLEAR);
        report1.setAdjudication(Report.Adjudication.ENGAGED);
        report1.setReportPackage("Package A");
        report1.setCompletedDate(LocalDateTime.now());
        Throwable exception= assertThrows(ConstraintViolationException.class,()->reportService.updateByCandidateId(id,report1));
        assertEquals("turnAroundTime: must not be null",exception.getMessage());
    }


}
