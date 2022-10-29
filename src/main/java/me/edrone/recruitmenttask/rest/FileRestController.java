package me.edrone.recruitmenttask.rest;


import me.edrone.recruitmenttask.dto.FileEntityDto;
import me.edrone.recruitmenttask.service.FileService;
import me.edrone.recruitmenttask.service.LocalFileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;

@RestController
@RequestMapping("api/files")
public class FileRestController {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileRestController.class);

    private final FileService fileService;
    private final LocalFileService localFileService;

    public FileRestController(FileService fileService, LocalFileService localFileService) {
        this.fileService = fileService;
        this.localFileService = localFileService;
    }

    @GetMapping("jobs")
    public CompletableFuture<ResponseEntity<List<Long>>> getCurrentJobsIds() {
//        return ResponseEntity.ok(fileService.getIdsOfCurrentJobs());
        return fileService.getIdsOfCurrentJobs().thenApply(ResponseEntity::ok).exceptionally(handleGetCurrentJobsIdsFailure);
    }

    private Function<Throwable, ? extends ResponseEntity<List<Long>>> handleGetCurrentJobsIdsFailure = throwable -> {
        LOGGER.error("Failed to load current jobs Ids: {}", throwable);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    };


    @GetMapping()
    public CompletableFuture<ResponseEntity<List<FileEntityDto>>> getCurrentJobsResults() {
        return fileService.getAllCurrentJobs().thenApply(ResponseEntity::ok);
    }

    @PostMapping("create")
    public ResponseEntity<FileEntityDto> createJob(@RequestBody FileEntityDto fileEntityDto) {
        CompletableFuture<FileEntityDto> created = fileService.create(fileEntityDto);
        try {
            localFileService.createFile(created);
            return created != null ?
                    ResponseEntity.status(HttpStatus.CREATED).body(created.get()) :
                    ResponseEntity.badRequest().build();
        } catch (ExecutionException e) {
            LOGGER.error("Failed to save Job");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
