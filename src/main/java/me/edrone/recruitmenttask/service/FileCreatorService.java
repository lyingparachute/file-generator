package me.edrone.recruitmenttask.service;

import org.springframework.stereotype.Service;

import java.util.stream.IntStream;
import java.util.stream.LongStream;


@Service
public class FileCreatorService {

    public Integer numberOfPossibleCombinations(char[] chars, int lengthOfResultString) {
        int numOfChars = chars.length;
        int subtractionResult = Math.subtractExact(numOfChars, lengthOfResultString);
        return Math.floorDiv(calculateFactorial(numOfChars),calculateFactorial(subtractionResult));

    }


    public Integer calculateFactorial(int basis) {
        return IntStream.rangeClosed(1, basis)
                .reduce(1, (int x, int y) -> x * y);
    }


}
