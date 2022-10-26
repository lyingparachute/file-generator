package me.edrone.recruitmenttask.service;

import me.edrone.recruitmenttask.dto.FileDto;
import me.edrone.recruitmenttask.entity.FileEntity;
import org.springframework.stereotype.Component;

@Component
public class FileMapper {

    public FileDto toDto(FileEntity fileEntity) {
        FileDto dto = new FileDto();
        dto.setId(fileEntity.getId());
        dto.setAvailableChars(fileEntity.getAvailableChars());
        dto.setMinLengthOfTargetString(fileEntity.getMinLengthOfTargetString());
        dto.setMaxLengthOfTargetString(fileEntity.getMaxLengthOfTargetString());
        dto.setNumberOfTargetStrings(fileEntity.getNumberOfTargetStrings());
        return dto;
    }

    public FileEntity toEntity(FileDto fileDto) {
        FileEntity entity = new FileEntity();
        entity.setId(fileDto.getId());
        entity.setAvailableChars(fileDto.getAvailableChars());
        entity.setMinLengthOfTargetString(fileDto.getMinLengthOfTargetString());
        entity.setMaxLengthOfTargetString(fileDto.getMaxLengthOfTargetString());
        entity.setNumberOfTargetStrings(fileDto.getNumberOfTargetStrings());
        return entity;
    }

}
