package me.edrone.recruitmenttask.service;

import me.edrone.recruitmenttask.dto.FileDto;
import me.edrone.recruitmenttask.entity.FileEntity;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;

@Component
public class LocalFile {

    private final String FILE_PATH = "src/main/resources/files/";


    public BufferedWriter createFile(FileDto fileDto) {
        createDirectory();
        String fileName = FILE_PATH + fileDto.getId() + ".txt";
        Set<String> setOfStrings = fileDto.getSetOfStrings();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            for (String s : setOfStrings) {
                writer.write(s);
                writer.newLine();
            }
            return writer;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void createDirectory() {
        new File(FILE_PATH).mkdirs();
    }
}
