package me.edrone.recruitmenttask.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class FileCreatorServiceTest {

    @Autowired
    FileCreatorService fileCreatorService;


    @Test
    void getNumberOfPossibleCombinationsGivenCharArrayAndLengthOfTargetString() {
        //given
        char[] givenChars = {'a', 'b', 'c', 'd', 'e'};
        int targetStringLength = 3;
        Integer expected = 60;
        //when
        Integer actual = fileCreatorService.getNumberOfPossibleCombinations(givenChars, targetStringLength);
        //then
        assertThat(actual).isEqualTo(expected);
        assertThat(fileCreatorService.getNumberOfPossibleCombinations(givenChars, 4)).isEqualTo(120);
        assertThat(fileCreatorService.getNumberOfPossibleCombinations(givenChars, 5)).isEqualTo(120);
    }

    @Test
    void getNumberOfPossibleCombinationsVariationGivenCharArrayAndLengthOfTargetString() {
        //given
        char[] givenChars = {'a', 'b', 'c', 'd', 'e'};
        int targetStringLength = 3;
        Integer expected = 125;
        //when
        Integer actual = fileCreatorService.getNumberOfPossibleCombinationsVariation(givenChars, targetStringLength);
        //then
        assertThat(actual).isEqualTo(expected);
        assertThat(fileCreatorService.getNumberOfPossibleCombinationsVariation(givenChars, 4)).isEqualTo(625);
    }

    @Test
    void calculateFactorialWhenGivenBasis() {
        //given
        int basisOfFactorial = 5;
        Integer expected = 120;
        //when
        Integer actual = fileCreatorService.calculateFactorial(basisOfFactorial);
        //then
        assertThat(actual).isEqualTo(expected);
        assertThat(fileCreatorService.calculateFactorial(0)).isEqualTo(1);
        assertThat(fileCreatorService.calculateFactorial(1)).isEqualTo(1);
        assertThat(fileCreatorService.calculateFactorial(4)).isEqualTo(24);
        assertThat(fileCreatorService.calculateFactorial(10)).isEqualTo(3628800);
    }

    @Test
    void getSetOfStringsGivenCharArrayLengthOfTargetStringAndDesiredNumberOfTargetStrings() throws Exception{
        //given
        char[] givenChars = {'a', 'b', 'c', 'd', 'e'};
        int targetStringLength = 3;
        int numberOfTargetStrings = 125;
        //when
        HashSet<String> actual = fileCreatorService.
                getSetOfStrings(givenChars, targetStringLength, numberOfTargetStrings);
        //then
        assertThat(actual.size()).isEqualTo(numberOfTargetStrings);
    }


}