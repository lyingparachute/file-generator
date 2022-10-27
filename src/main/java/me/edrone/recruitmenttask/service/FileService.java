package me.edrone.recruitmenttask.service;

import me.edrone.recruitmenttask.dto.FileDto;
import me.edrone.recruitmenttask.entity.FileEntity;
import me.edrone.recruitmenttask.entity.StringEntity;
import me.edrone.recruitmenttask.exception.IllegalNumberOfTargetStringsException;
import me.edrone.recruitmenttask.repository.FileRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.stream.IntStream;


@Service
public class FileService {

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

    private boolean isNumberOfTargetStringsTooBig(char[] chars, int targetStringLength, int numberOfTargetStrings) {
        return numberOfTargetStrings > getNumberOfPossibleCombinationsVariation(chars, targetStringLength);
    }

    public List<FileDto> getAllCurrentJobs() {
        List<FileEntity> all = fileRepository.findAll();
        return fileMapper.toDto(all);
    }

    @Transactional
    public FileDto create(FileDto dto) {
        StringEntity stringEntity = new StringEntity();
        FileEntity file = fileMapper.toEntity(dto);
        FileEntity saved = fileRepository.save(file);
        HashSet<String> setOfStrings = getSetOfStrings(dto.getAvailableChars().toCharArray(), dto.getMinLengthOfTargetString(), dto.getNumberOfTargetStrings());

        return fileMapper.toDto(saved);
    }
}
