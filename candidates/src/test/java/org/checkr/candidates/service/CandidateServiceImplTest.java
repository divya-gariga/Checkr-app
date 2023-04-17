package org.checkr.candidates.service;

import org.checkr.candidates.entity.Candidate;
import org.checkr.candidates.exception.CandidateNotfoundException;
import org.checkr.candidates.repository.CandidateRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.Validator;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
class CandidateServiceImplTest {
    @Autowired
    private CandidateService candidateService;

    @MockBean
    private CandidateRepository candidateRepository;
    private Candidate candidate,candidate1;

    private List<Candidate> candidates = new ArrayList<>();

    @BeforeEach
    void setup(){
        candidate = new Candidate(1,1,"john","john@gmail.com",
                "987234", Date.valueOf("2000-02-04"), LocalDateTime.now(),"hyderabad","500091",
                "522-990-887","87656437",LocalDateTime.now());
    }
    @Test
    void findById(){
        int id=1;
        when(candidateRepository.findById(id)).thenReturn(Optional.of(candidate));
        assertEquals(Optional.of(candidate),candidateService.findById(1));
    }

    @Test
    void findByIdWithException(){
        int id=1;
        when(candidateRepository.findById(id)).thenReturn(Optional.empty());
        Throwable exception= assertThrows(CandidateNotfoundException.class,()->candidateService.findById(1));
        assertEquals("candidate not found with the given candidate id",exception.getMessage());
    }
    @Test
    void updateById() {
        int id=1;
        when(candidateRepository.findById(id)).thenReturn(Optional.of(candidate));
        assertEquals(Optional.of(candidate),candidateService.updateById(id,candidate));
    }
    @Test
    void updateByIdWithException() {
        int id=2;
        when(candidateRepository.findById(id)).thenReturn(Optional.empty());
        Throwable exception= assertThrows(CandidateNotfoundException.class,()->candidateService.updateById(id,candidate));
        assertEquals("candidate not found with the given id",exception.getMessage());
    }

    @Test
    void updateByIdWithValidationErrors() {
        int id=1;
        when(candidateRepository.findById(id)).thenReturn(Optional.of(candidate));
        Candidate candidate1= new Candidate();
        candidate1.setId(1);
        candidate1.setUserId(1);
        candidate1.setName("John Doe");
        candidate1.setEmail("john.doe@example.");
        candidate1.setPhone("123-456-7890");
        candidate1.setDob(new Date(1990, 1, 1));
        candidate1.setCreatedAt(LocalDateTime.now());
        candidate1.setLocation("New York");
        candidate1.setZipCode("12345");
        candidate1.setSocialSecurity("123-45-6789");
        candidate1.setDriverLicense("ABC123");
        candidate1.setDate(LocalDateTime.now());
        Throwable exception= assertThrows(ConstraintViolationException.class,()->candidateService.updateById(id,candidate1));
        assertEquals("email: must be a well-formed email address",exception.getMessage());
    }


    @Test
    void addCandidate() {
        candidate1 = new Candidate(2,1,"smith","smith@gmail.com",
                "987234", Date.valueOf("2000-02-04"), LocalDateTime.now(),"hyderabad","500091",
                "522-990-887","87656437",LocalDateTime.now());
        candidateRepository.save(candidate1);
        assertEquals("added candidate",candidateService.addCandidate(candidate1));
    }

    @Test
    void findByUserId(){
        int id=1;
        candidates = new ArrayList<>();
        candidates.add(candidate);
        when(candidateRepository.getByUserId(id)).thenReturn(Optional.of(candidate));
        when(candidateRepository.findByUserId(id)).thenReturn(candidates);
        assertEquals(candidates,candidateService.findByUserId(id));
    }

    @Test
    void findByUserIdWithException(){
        int id=1;
        when(candidateRepository.getByUserId(id)).thenReturn(Optional.empty());
        Throwable exception= assertThrows(CandidateNotfoundException.class,()->candidateService.findByUserId(id));
        assertEquals("candidate not found with the given user id",exception.getMessage());
    }
}
