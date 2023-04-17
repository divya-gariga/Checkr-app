package org.checkr.candidates.service;

import org.checkr.candidates.entity.Candidate;
import java.util.List;
import java.util.Optional;

public interface CandidateService {
    Optional<Candidate> updateById(int id,Candidate candidate);
    List<Candidate> findByUserId(int userId);
    Optional<Candidate> findById(int id);
    String addCandidate(Candidate candidate);
}
