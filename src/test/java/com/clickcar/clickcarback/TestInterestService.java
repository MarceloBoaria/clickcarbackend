package com.clickcar.clickcarback;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.clickcar.clickcarback.repositories.InterestRepository;
import com.clickcar.clickcarback.service.InterestService;

public class TestInterestService {

    @Mock
    InterestRepository interestRepository;

    @InjectMocks
    InterestService interestService;

    @Test
    public void testPurchase() {

        

    }
    
}
