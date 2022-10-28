package me.edrone.recruitmenttask.util;

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


    public BufferedWriter create(FileEntity fileEntity){
        new File(FILE_PATH).mkdirs();
        String fileName = FILE_PATH + fileEntity.getId() + ".txt";
        Set<String> setOfStrings = fileEntity.getSetOfStrings();
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
}
