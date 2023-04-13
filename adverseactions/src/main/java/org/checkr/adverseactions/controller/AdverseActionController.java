package org.checkr.adverseactions.controller;

import lombok.extern.slf4j.Slf4j;
import org.checkr.adverseactions.entity.AdverseAction;
import org.checkr.adverseactions.service.AdverseActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@RequestMapping("/api/adverseactions")
public class AdverseActionController {

    @Autowired
    private AdverseActionService adverseActionService;

    @GetMapping("/")
    public List<AdverseAction> getAllAdverseActions(){
        log.trace("request received at getByUserEmail");
        return adverseActionService.getAllAdverseActions();
    }
}
