package me.edrone.recruitmenttask.repository.util;

import me.edrone.recruitmenttask.dto.FileDto;
import me.edrone.recruitmenttask.repository.FileRepository;
import me.edrone.recruitmenttask.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.io.File;

@Component
public class InitData {

    public static final String FILE_NAME = "src/main/resources/files/1.txt";

    @Autowired
    private FileRepository fileRepository;

    @Autowired
    private FileService fileService;

    @Transactional
    public void cleanUp(){
        fileRepository.deleteAll();
    }

    public void cleanUpFiles() {
        new File(FILE_NAME).delete();
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
