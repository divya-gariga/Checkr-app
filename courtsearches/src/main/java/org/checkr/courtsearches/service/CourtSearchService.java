package org.checkr.courtsearches.service;

import org.checkr.courtsearches.entity.CourtSearch;

import java.util.List;

public interface CourtSearchService {
    List<CourtSearch> getByCandidateId(int candidateId);
}
