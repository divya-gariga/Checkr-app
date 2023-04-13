package org.checkr.courtsearches.controller;

import lombok.extern.slf4j.Slf4j;
import org.checkr.courtsearches.entity.CourtSearch;
import org.checkr.courtsearches.service.CourtSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@RequestMapping("/api/courtsearches")
@Validated
public class CourtSearchController
{
    @Autowired
    private CourtSearchService courtSearchService;

    @GetMapping("/{candidateId}")
    public List<CourtSearch> getAByCandidateId(@Valid @PathVariable int candidateId){
        return courtSearchService.getByCandidateId(candidateId);
    }
}
