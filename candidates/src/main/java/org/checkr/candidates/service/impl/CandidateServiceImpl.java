package org.checkr.candidates.service.impl;

import org.checkr.candidates.entity.Candidate;
import org.checkr.candidates.exception.CandidateNotfoundException;
import org.checkr.candidates.repository.CandidateRepository;
import org.checkr.candidates.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;
import java.util.Set;


@Service
public class CandidateServiceImpl implements CandidateService {
    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private Validator validator;
    @Override
    public Optional<Candidate> updateById(int id, Candidate candidate) {
        Optional<Candidate> result = candidateRepository.findById(id);
        Set<ConstraintViolation<Candidate>> violations = validator.validate(candidate);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
        if(result.isPresent()) {
            LocalDateTime dateTime=LocalDateTime.now();
            candidate.setCreatedAt(dateTime);
            System.out.println(dateTime);
            candidateRepository.save(candidate);
            return candidateRepository.findById(id);
        }
        else{
            throw new CandidateNotfoundException("candidate not found with the given id");
        }
    }

    @Override
    public List<Candidate> findByUserId(int userId) {
        Optional<Candidate> result = candidateRepository.getByUserId(userId);
        if(result.isPresent()){
            return candidateRepository.findByUserId(userId);
        }
        else {
            throw new CandidateNotfoundException("candidate not found with the given user id");
        }
    }

    @Override
    public Optional<Candidate> findById(int id) {
        Optional<Candidate> result = candidateRepository.findById(id);
        if(result.isPresent()){
            return candidateRepository.findById(id);
        }
        else {
            throw new CandidateNotfoundException("candidate not found with the given id");
        }
    }

    @Override
    public String addCandidate(Candidate candidate) {
        LocalDateTime dateTime=LocalDateTime.now();
        candidate.setCreatedAt(dateTime);
        candidateRepository.save(candidate);
        return "added candidate";
    }
}
