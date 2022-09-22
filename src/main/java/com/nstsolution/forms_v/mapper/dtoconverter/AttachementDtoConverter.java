package com.nstsolution.forms_v.mapper.dtoconverter;

import com.nstsolution.forms_v.mapper.AttachementDto;
import com.nstsolution.forms_v.mapper.dtoconverter.basedtoconverter.BaseDtoConverter;
import com.nstsolution.forms_v.model.AttachementEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AttachementDtoConverter extends BaseDtoConverter<AttachementEntity, AttachementDto> {


    @Override
    public AttachementDto convertToDto(AttachementEntity attachementEntity){
        AttachementDto attachementDto = new AttachementDto();
        attachementDto.setId(attachementEntity.getId());
        attachementDto.setAttachementBlob(attachementEntity.getAttachementBlob());
        attachementDto.setNameAttachement(attachementEntity.getNameAttachement());
        attachementDto.setAttachementObj(attachementEntity.getAttachementObj());
        attachementDto.setExtension(attachementEntity.getExtension());
        return attachementDto;
    }

    @Override
    public AttachementEntity convertToEntity(AttachementDto dto) {
        AttachementEntity attachement = new AttachementEntity();
        attachement.setId(dto.getId());
        attachement.setAttachementBlob(dto.getAttachementBlob());
        attachement.setNameAttachement(dto.getNameAttachement());
        attachement.setAttachementObj(dto.getAttachementObj());
        attachement.setExtension(dto.getExtension());
        return attachement;
    }

    @Override
    public List<AttachementDto> convertListDtos(List<AttachementEntity> entity) {
        return entity.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<AttachementEntity> convertListEntities(List<AttachementDto> dto) {
        return dto.stream()
                .map(this::convertToEntity)
                .collect(Collectors.toList());
    }

}
