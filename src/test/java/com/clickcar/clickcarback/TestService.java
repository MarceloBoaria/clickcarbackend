package com.clickcar.clickcarback;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.ContextConfiguration;

import com.clickcar.clickcarback.repositories.UserRepository;

@ContextConfiguration(classes = {UserRepository.class})
public class TestService {

    protected double fValue1 = 2.0;
    protected double fValue2 = 3.0;  

    @Test
    public void testAdd() {
        assertEquals(fValue1 + fValue2, 5.0);
    }
    
}
