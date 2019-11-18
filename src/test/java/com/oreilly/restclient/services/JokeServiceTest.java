package com.oreilly.restclient.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class JokeServiceTest {
    // used to log data
    private Logger logger = LoggerFactory.getLogger(JokeServiceTest.class);

    @Autowired
    private JokeService service;

    @Test
    void getJoke() {
        // uses the JokeService to make a REST call with the passed in variables
        String joke =  service.getJoke("Craig", "Walls");
        logger.info(joke);
        // Checks that what's returned contains the correct strings
        assertTrue(joke.contains("Craig") || joke.contains("Walls"));
    }
}
