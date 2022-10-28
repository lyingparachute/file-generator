package me.edrone.recruitmenttask.rest;


import me.edrone.recruitmenttask.dto.FileDto;
import me.edrone.recruitmenttask.service.FileService;
import me.edrone.recruitmenttask.service.LocalFileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/files")
public class FileRestController {

    private final FileService fileService;
    private final LocalFileService localFileService;

    public FileRestController(FileService fileService, LocalFileService localFileService) {
        this.fileService = fileService;
        this.localFileService = localFileService;
    }

    @GetMapping("jobs")
    public ResponseEntity<List<Long>> getCurrentJobs() {
        return ResponseEntity.ok(fileService.getIdsOfCurrentJobs());
    }

    @GetMapping()
    public ResponseEntity<List<FileDto>> getCurrentJobsResults() {
        return ResponseEntity.ok(fileService.getAllCurrentJobs());
    }

    @PostMapping("create")
    public ResponseEntity<FileDto> createJob(@RequestBody FileDto fileDto) {
        FileDto created = fileService.create(fileDto);
        localFileService.createFile(created);
        return created != null ?
                ResponseEntity.status(HttpStatus.CREATED).body(created) :
                ResponseEntity.badRequest().build();
    }
}
