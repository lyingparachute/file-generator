package me.edrone.recruitmenttask.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.Access;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FileCreatorServiceTest {

    @Autowired
    FileCreatorService fileCreatorService;


    @Test
    void getNumberOfPossibleCombinationsGivenCharArrayAndLengthOfResultString() {
        //given
        char[] givenChars = {'a', 'b', 'c', 'd', 'e'};
        int lengthOfResultString = 3;
        Integer expected = 60;
        //when
        Integer actual = fileCreatorService.numberOfPossibleCombinations(givenChars, lengthOfResultString);
        //then
        assertThat(actual).isEqualTo(expected);
        assertThat(fileCreatorService.numberOfPossibleCombinations(givenChars, 4)).isEqualTo(120);
        assertThat(fileCreatorService.numberOfPossibleCombinations(givenChars, 5)).isEqualTo(120);
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
}