package me.edrone.recruitmenttask.dto;


import lombok.Data;
import me.edrone.recruitmenttask.entity.StringEntity;

import java.util.HashSet;
import java.util.Set;

@Data
public class FileDto {
    private Long id;
    private String availableChars;
    private int minLengthOfTargetString;
    private int maxLengthOfTargetString;
    private int numberOfTargetStrings;
    private Set<StringEntity> setOfStrings = new HashSet<>();
}
