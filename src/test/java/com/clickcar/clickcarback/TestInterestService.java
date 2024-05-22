package com.clickcar.clickcarback;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.clickcar.clickcarback.repositories.FavoriteRepository;
import com.clickcar.clickcarback.service.FavoriteService;

public class TestInterestService {

    @Mock
    FavoriteRepository interestRepository;

    @InjectMocks
    FavoriteService interestService;

    @Test
    public void testPurchase() {

        

    }
    
}
