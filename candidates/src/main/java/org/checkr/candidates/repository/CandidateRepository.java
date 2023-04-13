package org.checkr.candidates.repository;

import org.checkr.candidates.entity.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Integer> {

    List<Candidate> findByUserId(int userId);

    Optional<Candidate> getByUserId(int userId);

//    Optional<Candidate> findByCandidateId(int id);
}