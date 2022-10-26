package me.edrone.recruitmenttask.rest;


import me.edrone.recruitmenttask.dto.FileCreationDto;
import me.edrone.recruitmenttask.service.FileCreatorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/file")
public class FileCreationRestController {

    FileCreatorService fileCreatorService;

    public FileCreationRestController(FileCreatorService fileCreatorService) {
        this.fileCreatorService = fileCreatorService;
    }

    @GetMapping
    public ResponseEntity<FileCreationDto> getCurrentJobs() {
        return null;
    }

}
