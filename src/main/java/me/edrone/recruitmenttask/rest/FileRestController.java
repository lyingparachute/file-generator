package me.edrone.recruitmenttask.rest;


import me.edrone.recruitmenttask.dto.FileDto;
import me.edrone.recruitmenttask.service.FileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/files")
public class FileRestController {

    private final FileService fileService;

    public FileRestController(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping("jobs")
    public ResponseEntity<FileDto> getCurrentJobs() {
        return null;
    }

    @GetMapping()
    public ResponseEntity<FileDto> getCurrentJobsResults() {
        return null;
    }

    @PostMapping("create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<FileDto> createJob(@RequestBody FileDto fileDto) {


        return null;
    }
}
