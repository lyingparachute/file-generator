package me.edrone.recruitmenttask.dto;


import lombok.Data;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.HashSet;
import java.util.Set;

@Data
public class FileEntityDto {
    private Long id;
    private String availableChars;
    private int minLengthOfTargetString;
    private int maxLengthOfTargetString;
    private int numberOfTargetStrings;
    private Set<String> setOfStrings = new HashSet<>();
    private OffsetDateTime createdAt;

    public String getCreatedAt() {
        return createdAt.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.SHORT));
    }

}
