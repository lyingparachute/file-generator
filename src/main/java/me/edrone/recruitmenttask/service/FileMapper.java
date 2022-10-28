package me.edrone.recruitmenttask.service;

import me.edrone.recruitmenttask.dto.FileDto;
import me.edrone.recruitmenttask.entity.FileEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FileMapper {

    public FileDto toDto(FileEntity fileEntity) {
        FileDto dto = new FileDto();
        dto.setId(fileEntity.getId());
        dto.setAvailableChars(fileEntity.getAvailableChars());
        dto.setMinLengthOfTargetString(fileEntity.getMinLengthOfTargetString());
        dto.setMaxLengthOfTargetString(fileEntity.getMaxLengthOfTargetString());
        dto.setNumberOfTargetStrings(fileEntity.getNumberOfTargetStrings());
        dto.setSetOfStrings(fileEntity.getSetOfStrings());
        return dto;
    }

    public List<FileDto> toDto(List<FileEntity> files) {
        return files.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public FileEntity toEntity(FileDto fileDto) {
        FileEntity entity = new FileEntity();
        entity.setId(fileDto.getId());
        entity.setAvailableChars(fileDto.getAvailableChars());
        entity.setMinLengthOfTargetString(fileDto.getMinLengthOfTargetString());
        entity.setMaxLengthOfTargetString(fileDto.getMaxLengthOfTargetString());
        entity.setNumberOfTargetStrings(fileDto.getNumberOfTargetStrings());
        entity.setSetOfStrings(fileDto.getSetOfStrings());
        return entity;
    }

}
