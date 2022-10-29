package me.edrone.recruitmenttask.dto;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class FileEntityDto {
    private Long id;
    private String availableChars;
    private int minLengthOfTargetString;
    private int maxLengthOfTargetString;
    private int numberOfTargetStrings;
    private Set<String> setOfStrings = new HashSet<>();
    private OffsetDateTime createdAt;
}
