package me.edrone.recruitmenttask.rest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FileRestControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @BeforeEach
    public void setUp() {

    }

    @LocalServerPort
    int randomServerPort;

    @Test
    void getCurrentJobs() {
    }

    @Test
    void getCurrentJobsResults() {
    }

    @Test
    void createJob() {
    }


}