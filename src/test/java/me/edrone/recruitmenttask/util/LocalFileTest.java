package me.edrone.recruitmenttask.util;

import me.edrone.recruitmenttask.dto.FileDto;
import me.edrone.recruitmenttask.entity.FileEntity;
import me.edrone.recruitmenttask.repository.FileRepository;
import me.edrone.recruitmenttask.service.FileService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.BufferedWriter;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LocalFileTest {

    @Autowired
    LocalFile localFile;

    @Autowired
    FileService fileService;

    @Autowired
    FileRepository fileRepository;

    private final String FILE_NAME = "src/main/resources/files/1.txt";

    @AfterEach
    public void cleanUpFiles() {
        File targetFile = new File(FILE_NAME);
        targetFile.delete();
    }

    @Test
    void createLocalFile_givenFileEntity() {
        //given
        FileDto fileDto = createFileDto();

        //when
        BufferedWriter created = localFile.createFile(fileDto);

        //then
        assertThat(created).isNotNull();
        assertTrue(Files.exists(Path.of(FILE_NAME)));
        assertThat(new File(FILE_NAME)).isFile();

    }

    public FileDto createFileDto () {
        FileDto fileDto = new FileDto();
        fileDto.setAvailableChars("abcde");
        fileDto.setMinLengthOfTargetString(2);
        fileDto.setMaxLengthOfTargetString(3);
        fileDto.setNumberOfTargetStrings(5);
        return fileService.create(fileDto);
    }
}