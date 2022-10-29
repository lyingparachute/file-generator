package me.edrone.recruitmenttask.rest;

import me.edrone.recruitmenttask.dto.FileEntityDto;
import me.edrone.recruitmenttask.repository.FileRepository;
import me.edrone.recruitmenttask.repository.util.InitData;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FileRestControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private FileRepository fileRepository;

    @Autowired
    private InitData initData;

    @LocalServerPort
    int randomServerPort;

    @BeforeEach
    @AfterEach
    public void setUp() {
        initData.cleanUpRepository();
        initData.cleanUpFiles();
    }

    @Test
    void shouldGetCurrentJobsIds() throws URISyntaxException, ExecutionException, InterruptedException {
        //given
        FileEntityDto fileEntityDto = initData.createFileDto();

        //when
        URI url = createUrl("/api/files/jobs/");
        ResponseEntity<List> response = restTemplate.getForEntity(url, List.class);

        //then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        List actual = response.getBody();
        assertThat(actual).isNotNull();
    }


    @Test
    void shouldGetCurrentJobsResults() throws URISyntaxException {
        //given
        FileEntityDto fileEntityDto = initData.createFileDto();

        //when
        URI url = createUrl("/api/files/");
        ResponseEntity<Set> response = restTemplate.getForEntity(url, Set.class);

        //then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Set actual = response.getBody();
        assertThat(actual).isNotNull();
        assertThat(actual.isEmpty()).isFalse();
    }

    @Test
    void shouldCreateJob_givenEntity() throws URISyntaxException {
        //given
        FileEntityDto fileEntityDto = initData.createFileDto();

        //when
        URI url = createUrl("/api/files/create");
        ResponseEntity<FileEntityDto> response = restTemplate.postForEntity(url, fileEntityDto, FileEntityDto.class);

        //then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        FileEntityDto actual = response.getBody();
        assertThat(actual).isNotNull();
        assertThat(actual.getId()).isNotNull();
        fileRepository.findById(actual.getId())
                .orElseThrow(() -> new IllegalStateException(
                        "File with ID: " + actual.getId() + " should not be missing"));
        assertThat(actual.getAvailableChars()).isEqualTo(fileEntityDto.getAvailableChars());
        assertThat(actual.getMinLengthOfTargetString()).isEqualTo(fileEntityDto.getMinLengthOfTargetString());
        assertThat(actual.getMaxLengthOfTargetString()).isEqualTo(fileEntityDto.getMaxLengthOfTargetString());
        assertThat(actual.getNumberOfTargetStrings()).isEqualTo(fileEntityDto.getNumberOfTargetStrings());
    }



    private URI createUrl(String path) throws URISyntaxException {
        return new URI("http://localhost:" + randomServerPort + path);
    }

}