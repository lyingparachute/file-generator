package me.edrone.recruitmenttask.service;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.stream.IntStream;


@Service
public class FileCreatorService {

    // Number of possible combinations without repeating letters
    // for example: from String "abc" -> "aaa" is NOT possible
    public Integer getNumberOfPossibleCombinations(char[] chars, int targetStringLength) {
        int numOfChars = chars.length;
        int subtractionResult = Math.subtractExact(numOfChars, targetStringLength);
        return Math.floorDiv(calculateFactorial(numOfChars),calculateFactorial(subtractionResult));
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

    public HashSet<String> getSetOfStrings(char[] chars, int targetStringLength, int numberOfTargetStrings) throws Exception{
        HashSet<String> setOfStrings = new HashSet<>();
        if (isNumberOfTargetStringsTooBig(chars, targetStringLength, numberOfTargetStrings)){
            throw new Exception();
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

}
