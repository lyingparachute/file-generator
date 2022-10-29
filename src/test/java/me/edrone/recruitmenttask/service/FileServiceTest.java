package me.edrone.recruitmenttask.service;

import me.edrone.recruitmenttask.exception.IllegalNumberOfTargetStringsException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class FileServiceTest {

    @Autowired
    FileService fileService;


    @Test
    void returnNumberOfPossibleCombinations_givenCharArrayAndLengthOfTargetString() {
        //given
        char[] givenChars = {'a', 'b', 'c', 'd', 'e'};
        int targetStringLength = 3;
        Integer expected = 60;

        //when
        Integer actual = fileService.getNumberOfPossibleCombinations(givenChars, targetStringLength);

        //then
        assertThat(actual).isEqualTo(expected);
        assertThat(fileService.getNumberOfPossibleCombinations(givenChars, 4)).isEqualTo(120);
        assertThat(fileService.getNumberOfPossibleCombinations(givenChars, 5)).isEqualTo(120);
    }

    @Test
    void returnNumberOfPossibleCombinationsVariation_givenCharArrayAndLengthOfTargetString() {
        //given
        char[] givenChars = {'a', 'b', 'c', 'd', 'e'};
        int targetStringLength = 3;
        Integer expected = 125;

        //when
        Integer actual = fileService.getNumberOfPossibleCombinationsVariation(givenChars, targetStringLength);

        //then
        assertThat(actual).isEqualTo(expected);
        assertThat(fileService.getNumberOfPossibleCombinationsVariation(givenChars, 4)).isEqualTo(625);
    }

    @Test
    void returnNumberOfPossibleCombinationsVariation_givenCharArrayMinAndMaxLengthOfTargetString() {
        //given
        char[] givenChars = {'a', 'b', 'c', 'd', 'e'};
        int minTargetStringLength = 2;
        int maxTargetStringLength = 4;
        Integer expectedResult = 775;

        //when
        Integer actualResult = fileService.getNumberOfPossibleCombinationsVariation(givenChars, minTargetStringLength, maxTargetStringLength);

        //then
        assertThat(actualResult).isEqualTo(expectedResult);
        assertThat(fileService.getNumberOfPossibleCombinationsVariation(givenChars, 4, 4)).isEqualTo(625);
    }

    @Test
    void returnFactorial_givenBasis() {
        //given
        int basisOfFactorial = 5;
        Integer expected = 120;

        //when
        Integer actual = fileService.calculateFactorial(basisOfFactorial);

        //then
        assertThat(actual).isEqualTo(expected);
        assertThat(fileService.calculateFactorial(0)).isEqualTo(1);
        assertThat(fileService.calculateFactorial(1)).isEqualTo(1);
        assertThat(fileService.calculateFactorial(4)).isEqualTo(24);
        assertThat(fileService.calculateFactorial(10)).isEqualTo(3628800);
    }

    @Test
    void returnSetOfStrings_givenCharArrayLengthOfTargetStringAndDesiredNumberOfTargetStrings() throws IllegalNumberOfTargetStringsException {
        //given
        char[] givenChars = {'a', 'b', 'c', 'd', 'e'};
        int targetStringLength = 3;
        int numberOfTargetStrings = 125;

        //when
        HashSet<String> actual = fileService.
                getSetOfStrings(givenChars, targetStringLength, numberOfTargetStrings);

        //then
        assertThat(actual.size()).isEqualTo(numberOfTargetStrings);
        assertThat(actual).contains("aaa", "bbb", "ccc", "ddd", "eee", "aae", "eaa", "aee", "eea");
    }

    @Test
    void returnSetOfStrings_givenCharArrayMinMaxLengthOfTargetStringAndDesiredNumberOfTargetStrings() throws IllegalNumberOfTargetStringsException {
        //given
        char[] givenChars = {'a', 'b', 'c', 'd', 'e'};
        int minTargetStringLength = 2;
        int maxTargetStringLength = 4;
        int numberOfTargetStrings = 775;

        //when
        HashSet<String> actual = fileService.
                getSetOfStrings(givenChars, minTargetStringLength, maxTargetStringLength, numberOfTargetStrings);

        //then
        assertThat(actual.size()).isEqualTo(numberOfTargetStrings);
        assertThat(actual).contains("aaaa", "bb", "ccc", "dddd", "ee", "aaee", "eaaa", "aee", "ea");
    }

    @Test
    void returnIllegalNumberOfTargetStringsException_givenWrongParameters() {
        //given
        char[] givenChars = {'a', 'b', 'c', 'd', 'e'};
        int targetStringLength = 3;
        int numberOfTargetStrings = 126;
        String expectedMessage = "Number of target strings is invalid or is doesn't match number of possible results.";

        //when
        IllegalNumberOfTargetStringsException exception = assertThrows(IllegalNumberOfTargetStringsException.class, () -> {
            fileService.getSetOfStrings(givenChars, targetStringLength, numberOfTargetStrings);
        });

        //then
        String actualMessage = exception.getMessage();
        assertThat(actualMessage).contains(expectedMessage);
    }


}