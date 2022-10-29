package me.edrone.recruitmenttask.service;

import me.edrone.recruitmenttask.dto.FileDto;
import me.edrone.recruitmenttask.repository.FileRepository;
import me.edrone.recruitmenttask.repository.util.InitData;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.BufferedWriter;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

import static me.edrone.recruitmenttask.repository.util.InitData.FILE_DIRECTORY;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LocalFileServiceTest {

    @Autowired
    LocalFileService localFileService;

    @Autowired
    FileService fileService;

    @Autowired
    InitData initData;

    @Autowired
    FileRepository fileRepository;

    @AfterEach
    @BeforeEach
    public void cleanUp() {
        initData.cleanUpFiles();
        initData.cleanUpRepository();
    }

    @Test
    void createLocalFile_givenFileEntity() {
        //given
        FileDto fileDto = initData.createFileDto();

        //when
        BufferedWriter created = localFileService.createFile(fileDto);

        //then
        assertThat(created).isNotNull();
        String FILE_NAME = FILE_DIRECTORY + fileDto.getId() + ".txt";
        assertTrue(Files.exists(Path.of(FILE_NAME)));
        assertThat(new File(FILE_NAME)).isFile();

    }
}