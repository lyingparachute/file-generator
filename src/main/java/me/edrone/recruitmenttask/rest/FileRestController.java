package me.edrone.recruitmenttask.rest;


import me.edrone.recruitmenttask.dto.FileDto;
import me.edrone.recruitmenttask.service.FileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/files")
public class FileRestController {

    private final FileService fileService;

    public FileRestController(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping("jobs")
    public ResponseEntity<List<FileDto>> getCurrentJobs() {
        return ResponseEntity.ok(fileService.getAllCurrentJobs());
    }

    @GetMapping()
    public ResponseEntity<List<FileDto>> getCurrentJobsResults() {
        return ResponseEntity.ok(fileService.getAllCurrentJobsWithStrings());
    }

    @PostMapping("create")
    public ResponseEntity<FileDto> createJob(@RequestBody FileDto fileDto) {
        FileDto created = fileService.create(fileDto);
        return created != null ?
                ResponseEntity.status(HttpStatus.CREATED).body(created) :
                ResponseEntity.badRequest().build();
    }
}
