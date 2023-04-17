package org.checkr.courtsearches.service;

import org.checkr.courtsearches.entity.CourtSearch;
import org.checkr.courtsearches.repository.CourtSearchRepository;
import org.checkr.courtsearches.service.CourtSearchService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class CourtSearchServiceImplTests {

    @Autowired
    private CourtSearchService courtSearchService;
    @MockBean
    private CourtSearchRepository courtSearchRepository;
    @Test
    void getByCandidateId() {
        int id=1;
        CourtSearch courtSearch = new CourtSearch();
        List<CourtSearch> courtSearchArrayList = new ArrayList<>();
        courtSearchArrayList.add(courtSearch);
        when(courtSearchRepository.findAllByCandidateId(id)).thenReturn(courtSearchArrayList);
        assertEquals(courtSearchArrayList,courtSearchService.getByCandidateId(id));
    }
}
