package me.edrone.recruitmenttask.dto;


import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class FileDto {
    private Long id;
    private String availableChars;
    private int minLengthOfTargetString;
    private int maxLengthOfTargetString;
    private int numberOfTargetStrings;
    private Set<String> setOfStrings = new HashSet<>();
}
