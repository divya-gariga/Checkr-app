package org.checkr.courtsearches.repository;

import org.checkr.courtsearches.entity.CourtSearch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface CourtSearchRepository extends JpaRepository<CourtSearch, Integer> {


    List<CourtSearch> findByCandidateId(int candidateId);

    List<CourtSearch> findAllByCandidateId(int candidateId);
}