package me.edrone.recruitmenttask.repository.util;

import me.edrone.recruitmenttask.dto.FileDto;
import me.edrone.recruitmenttask.repository.FileRepository;
import me.edrone.recruitmenttask.service.FileService;
import org.aspectj.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.io.File;

@Component
public class InitData {
    public static final String FILE_DIRECTORY = "src/main/resources/files/";

    @Autowired
    private FileRepository fileRepository;

    @Autowired
    private FileService fileService;

    @Transactional
    public void cleanUpRepository(){
        fileRepository.deleteAll();
    }

    public void cleanUpFiles() {
        FileUtil.deleteContents(new File(FILE_DIRECTORY));
    }

    public FileDto createFileDto()  {
        FileDto fileDto = new FileDto();
        fileDto.setAvailableChars("abcde");
        fileDto.setMinLengthOfTargetString(2);
        fileDto.setMaxLengthOfTargetString(3);
        fileDto.setNumberOfTargetStrings(5);
        return fileService.create(fileDto);
    }





}
