package me.edrone.recruitmenttask.service;

import me.edrone.recruitmenttask.dto.FileEntityDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public class LocalFileService {

    private final String FILE_PATH = "src/main/resources/files/";
    private final Logger LOGGER = LoggerFactory.getLogger(LocalFileService.class);

    @Async
    public CompletableFuture<BufferedWriter> createFile(CompletableFuture<FileEntityDto> fileDto) {
        createDirectory();
        String fileNameWithPath;
        String fileName;
        Set<String> setOfStrings;

        try {
            fileName = fileDto.get().getId() + ".txt";
            fileNameWithPath = FILE_PATH + fileName;
            setOfStrings = fileDto.get().getSetOfStrings();
        } catch (InterruptedException | ExecutionException e) {
            LOGGER.error("Could not save local file file due to concurrency");
            throw new RuntimeException(e);
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileNameWithPath, true))) {
            for (String s : setOfStrings) {
                writer.write(s);
                writer.newLine();
            }
            LOGGER.info("File {} saved successfully to a local directory {}", fileName, FILE_PATH);
            return CompletableFuture.completedFuture(writer);
        } catch (IOException e) {
            LOGGER.error("Could not save local file file due to IOException");
            throw new RuntimeException(e);
        }
    }

    private void createDirectory() {
        new File(FILE_PATH).mkdirs();
    }
}
