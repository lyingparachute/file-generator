package me.edrone.recruitmenttask.service;

import me.edrone.recruitmenttask.dto.FileEntityDto;
import me.edrone.recruitmenttask.entity.FileEntity;
import me.edrone.recruitmenttask.exception.IllegalNumberOfTargetStringsException;
import me.edrone.recruitmenttask.repository.FileRepository;
import me.edrone.recruitmenttask.util.FileMapper;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.stream.IntStream;


@Service
public class FileService {

    private final static Logger LOGGER = LoggerFactory.getLogger(FileService.class);

    private final FileRepository fileRepository;
    private final FileMapper fileMapper;


    public FileService(FileRepository fileRepository, FileMapper fileMapper) {
        this.fileRepository = fileRepository;
        this.fileMapper = fileMapper;
    }

    // Number of possible combinations without repeating letters
    // for example: from String "abc" -> "aaa" is NOT possible
    public Integer getNumberOfPossibleCombinations(char[] chars, int targetStringLength) {
        int numOfChars = chars.length;
        int subtractionResult = Math.subtractExact(numOfChars, targetStringLength);
        return Math.floorDiv(calculateFactorial(numOfChars), calculateFactorial(subtractionResult));
    }

    // Number of possible combinations with possibility of repeating letters
    // for example: from String "abc" -> "aaa" is possible
    public Integer getNumberOfPossibleCombinationsVariation(char[] chars, int targetStringLength) {
        return (int) Math.pow(chars.length, targetStringLength);
    }

    public Integer getNumberOfPossibleCombinationsVariation(char[] chars, int minTargetStringLength, int maxTargetStringLength) {
        int result = 0;
        for(int numberOfCalculations = maxTargetStringLength-minTargetStringLength;
            maxTargetStringLength >= minTargetStringLength;
            maxTargetStringLength--) {
            result += (int) Math.pow(chars.length, maxTargetStringLength);
        }
        return result;
    }

    public Integer calculateFactorial(int basis) {
        return IntStream.rangeClosed(1, basis)
                .reduce(1, (int x, int y) -> x * y);
    }

    public HashSet<String> getSetOfStrings(char[] chars, int targetStringLength, int numberOfTargetStrings) throws IllegalNumberOfTargetStringsException {
        HashSet<String> setOfStrings = new HashSet<>();
        if (isNumberOfTargetStringsTooBig(chars, targetStringLength, numberOfTargetStrings)) {
            throw new IllegalNumberOfTargetStringsException();
        }
        while (setOfStrings.size() < numberOfTargetStrings) {
            String randomString = RandomStringUtils.random(targetStringLength, chars);
            setOfStrings.add(randomString);
        }
        return setOfStrings;
    }

    public HashSet<String> getSetOfStrings(char[] chars, int minTargetStringLength, int maxTargetStringLength, int numberOfTargetStrings) throws IllegalNumberOfTargetStringsException {
        HashSet<String> setOfStrings = new HashSet<>();
        if (isNumberOfTargetStringsTooBig(chars, minTargetStringLength, maxTargetStringLength, numberOfTargetStrings)) {
            throw new IllegalNumberOfTargetStringsException();
        }
        while (setOfStrings.size() < numberOfTargetStrings) {
            int randomStringLength = getRandomInt(minTargetStringLength, maxTargetStringLength);
            String randomString = RandomStringUtils.random(randomStringLength, chars);
            setOfStrings.add(randomString);
        }
        return setOfStrings;
    }

    @Async
    public CompletableFuture<List<FileEntityDto>> getAllCurrentJobs() {
        LOGGER.info("Request to get all files");
        List<Set<String>> sets = fileRepository.findAll().stream().map(FileEntity::getSetOfStrings).toList();
        List<FileEntity> all = fileRepository.findAll();
        return CompletableFuture.completedFuture(fileMapper.toDto(all));
    }

    @Async
    public CompletableFuture<List<Long>> getIdsOfCurrentJobs() {
        LOGGER.info("Request to get Ids of all files");
        return CompletableFuture.completedFuture(fileRepository.findAll().stream().map(FileEntity::getId).toList());
    }

    @Transactional
    @Async
    public CompletableFuture<FileEntityDto> create(FileEntityDto dto) {
        final long start = System.currentTimeMillis();
        LOGGER.info("Saving a file with number of target Strings: {} ", dto.getNumberOfTargetStrings());
        FileEntity file = fileMapper.toEntity(dto);
        HashSet<String> setOfStrings = getSetOfStrings(
                dto.getAvailableChars().toCharArray(),
                dto.getMinLengthOfTargetString(),
                dto.getMaxLengthOfTargetString(),
                dto.getNumberOfTargetStrings());
        file.setSetOfStrings(setOfStrings);
        FileEntity saved = fileRepository.save(file);
        LOGGER.info("Created a file in database with number of target Strings: {} and ID: {}.",
                saved.getNumberOfTargetStrings(), saved.getId());
        LOGGER.info("Elapsed process time: {} ms.", (System.currentTimeMillis() - start));
        return CompletableFuture.completedFuture(fileMapper.toDto(saved));
    }

    private boolean isNumberOfTargetStringsTooBig(char[] chars, int targetStringLength, int numberOfTargetStrings) {
        return numberOfTargetStrings > getNumberOfPossibleCombinationsVariation(chars, targetStringLength);
    }

    private boolean isNumberOfTargetStringsTooBig(char[] chars, int minTargetStringLength, int maxTargetStringLength, int numberOfTargetStrings) {
        return numberOfTargetStrings > getNumberOfPossibleCombinationsVariation(chars, minTargetStringLength, maxTargetStringLength);
    }

    private int getRandomInt(int min, int max) {
        return (int) Math.floor(Math.random() * (max - min + 1) + min);
    }

}
