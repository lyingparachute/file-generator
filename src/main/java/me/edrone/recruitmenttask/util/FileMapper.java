package me.edrone.recruitmenttask.util;

import me.edrone.recruitmenttask.dto.FileEntityDto;
import me.edrone.recruitmenttask.entity.FileEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FileMapper {

    public FileEntityDto toDto(FileEntity fileEntity) {
        FileEntityDto dto = new FileEntityDto();
        dto.setId(fileEntity.getId());
        dto.setAvailableChars(fileEntity.getAvailableChars());
        dto.setMinLengthOfTargetString(fileEntity.getMinLengthOfTargetString());
        dto.setMaxLengthOfTargetString(fileEntity.getMaxLengthOfTargetString());
        dto.setNumberOfTargetStrings(fileEntity.getNumberOfTargetStrings());
        dto.setSetOfStrings(fileEntity.getSetOfStrings());
        dto.setCreatedAt(fileEntity.getCreatedAt());
        return dto;
    }

    public List<FileEntityDto> toDto(List<FileEntity> files) {
        return files.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public FileEntity toEntity(FileEntityDto fileEntityDto) {
        FileEntity entity = new FileEntity();
        entity.setId(fileEntityDto.getId());
        entity.setAvailableChars(fileEntityDto.getAvailableChars());
        entity.setMinLengthOfTargetString(fileEntityDto.getMinLengthOfTargetString());
        entity.setMaxLengthOfTargetString(fileEntityDto.getMaxLengthOfTargetString());
        entity.setNumberOfTargetStrings(fileEntityDto.getNumberOfTargetStrings());
        entity.setSetOfStrings(fileEntityDto.getSetOfStrings());
        return entity;
    }

}
