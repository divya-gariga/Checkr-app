package org.checkr.adverseactions.service;

import org.checkr.adverseactions.entity.AdverseAction;
import org.checkr.adverseactions.repository.AdverseActionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class AdverseActionServiceImplTests {

    @Autowired
    private AdverseActionService adverseActionService;
    @MockBean
    private AdverseActionRepository adverseActionRepository;

    @Test
    void getAllAdverseActions(){

        AdverseAction adverseAction = new AdverseAction();
        List<AdverseAction> adverseActionList= new ArrayList<>();
        adverseActionList.add(adverseAction);
        when(adverseActionRepository.findAll()).thenReturn(adverseActionList);
        assertEquals(adverseActionList,adverseActionService.getAllAdverseActions());
    }
}
