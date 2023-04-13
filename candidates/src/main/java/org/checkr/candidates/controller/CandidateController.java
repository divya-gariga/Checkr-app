package org.checkr.candidates.controller;


import lombok.extern.slf4j.Slf4j;
import org.checkr.candidates.entity.Candidate;
import org.checkr.candidates.service.CandidateService;
import org.checkr.candidates.service.impl.CandidateServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@RequestMapping("/api/candidates")
@Validated
public class CandidateController {

    @Autowired
    private CandidateService candidateService;

    @GetMapping("/getbyuserid/{userId}")
    public List<Candidate> getCandidateByUserId(@Valid @NotEmpty  @PathVariable int userId){
        return candidateService.findByUserId(userId);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Candidate>> getById(@NotNull @NotEmpty @Valid @PathVariable int id){
        return new ResponseEntity<>(candidateService.findById(id), HttpStatus.FOUND);
    }
    @PutMapping("/{id}")
    public Optional<Candidate> updateById(@Valid @PathVariable int id, @Valid @RequestBody Candidate candidate){
        return candidateService.updateById(id,candidate);
    }
    @PostMapping("/addcandidate")
    public ResponseEntity<String> addCandidate(@Valid @RequestBody Candidate candidate){
        System.out.println("in controller"+candidate.getDob());
        return new ResponseEntity<>(candidateService.addCandidate(candidate),HttpStatus.CREATED);
    }
}
