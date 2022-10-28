package me.edrone.recruitmenttask.rest;

import me.edrone.recruitmenttask.dto.FileDto;
import me.edrone.recruitmenttask.entity.FileEntity;
import me.edrone.recruitmenttask.repository.FileRepository;
import me.edrone.recruitmenttask.repository.util.InitData;
import org.assertj.core.groups.Tuple;
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

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
        initData.cleanUp();
        initData.cleanUpFiles();
    }

    @Test
    void shouldGetCurrentJobsIds() throws URISyntaxException {
        //given
        FileDto fileDto = initData.createFileDto();

        //when
        URI url = createUrl("/api/files/jobs/");
        ResponseEntity<List> response = restTemplate.getForEntity(url, List.class);

        //then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        List actual = response.getBody();
        assertThat(actual).isNotNull();
    }



    @Test
    void getCurrentJobsResults() throws URISyntaxException {
        //given
        FileDto fileDto = initData.createFileDto();

        //when
        URI url = createUrl("/api/files/");
        ResponseEntity<Set> response = restTemplate.getForEntity(url, Set.class);

        //then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Set actual = response.getBody();
        assertThat(actual).isNotNull();
        assertThat(actual.isEmpty()).isFalse();
//        assertThat(actual).extracting(
//                FileEntity::getId,
//                FileEntity::getAvailableChars,
//                FileEntity::getMinLengthOfTargetString,
//                FileEntity::getMaxLengthOfTargetString,
//                FileEntity::getNumberOfTargetStrings)
//                .contains(
//                        Tuple.tuple(fileDto.getId(), fileDto.getAvailableChars(),
//                                fileDto.getMinLengthOfTargetString(), fileDto.getMaxLengthOfTargetString(),
//                                fileDto.getNumberOfTargetStrings()));
    }

    @Test
    void createJob() throws URISyntaxException {
        //given
        FileDto fileDto = initData.createFileDto();

        //when
        URI url = createUrl("/api/files/create");
        ResponseEntity<FileDto> response = restTemplate.postForEntity(url, fileDto, FileDto.class);

        //then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        FileDto actual = response.getBody();
        assertThat(actual).isNotNull();
        assertThat(actual.getId()).isNotNull();
        fileRepository.findById(actual.getId())
                .orElseThrow(() -> new IllegalStateException(
                        "File with ID: " + actual.getId() + " should not be missing"));
        assertThat(actual.getAvailableChars()).isEqualTo(fileDto.getAvailableChars());
        assertThat(actual.getMinLengthOfTargetString()).isEqualTo(fileDto.getMinLengthOfTargetString());
        assertThat(actual.getMaxLengthOfTargetString()).isEqualTo(fileDto.getMaxLengthOfTargetString());
        assertThat(actual.getNumberOfTargetStrings()).isEqualTo(fileDto.getNumberOfTargetStrings());
    }



    private URI createUrl(String path) throws URISyntaxException {
        return new URI("http://localhost:" + randomServerPort + path);
    }

}