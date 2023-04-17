package org.checkr.adverseactions.service.impl;

import org.checkr.adverseactions.entity.AdverseAction;
import org.checkr.adverseactions.repository.AdverseActionRepository;
import org.checkr.adverseactions.service.AdverseActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AdverseActionServiceImpl implements AdverseActionService {
    @Autowired
    private AdverseActionRepository adverseActionRepository;

    @Override
    public List<AdverseAction> getAllAdverseActions() {
        return adverseActionRepository.findAll();
    }

}
