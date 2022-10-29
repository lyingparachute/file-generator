package me.edrone.recruitmenttask.service;

import me.edrone.recruitmenttask.dto.FileEntityDto;
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
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static me.edrone.recruitmenttask.repository.util.InitData.FILE_DIRECTORY;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
    void createLocalFile_givenFileEntity() throws ExecutionException, InterruptedException {
        //given
        FileEntityDto fileEntityDto = initData.createFileDto();

        //when
        BufferedWriter created = localFileService.createFile(CompletableFuture.completedFuture(fileEntityDto));

        //then
        assertThat(created).isNotNull();
        String FILE_NAME = FILE_DIRECTORY + fileEntityDto.getId() + ".txt";
        assertTrue(Files.exists(Path.of(FILE_NAME)));
        assertThat(new File(FILE_NAME)).isFile();

    }
}