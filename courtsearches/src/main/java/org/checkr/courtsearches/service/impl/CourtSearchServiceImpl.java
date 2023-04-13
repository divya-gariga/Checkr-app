package org.checkr.courtsearches.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.checkr.courtsearches.entity.CourtSearch;
import org.checkr.courtsearches.repository.CourtSearchRepository;
import org.checkr.courtsearches.service.CourtSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
public class CourtSearchServiceImpl implements CourtSearchService {

    @Autowired
    private CourtSearchRepository courtSearchRepository;

    @Override
    public List<CourtSearch> getByCandidateId(int candidateId) {

        return courtSearchRepository.findAllByCandidateId(candidateId);
    }
}
