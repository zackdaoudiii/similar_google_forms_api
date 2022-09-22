package com.nstsolution.forms_v.utils.service;

import com.nstsolution.forms_v.mapper.AttachementDto;
import com.nstsolution.forms_v.mapper.dtoconverter.AttachementDtoConverter;
import com.nstsolution.forms_v.model.AttachementEntity;
import com.nstsolution.forms_v.repository.AttachementRepository;
import com.nstsolution.forms_v.utils.service.IService.IFileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.io.IOException;

@Service
public class FileUploadService implements IFileUploadService {

    private final AttachementRepository attachementRepository;
    private final AttachementDtoConverter attachementDtoConverter;

    @Autowired
    public FileUploadService(AttachementRepository attachementRepository, AttachementDtoConverter attachementDtoConverter) {
        this.attachementRepository = attachementRepository;
        this.attachementDtoConverter = attachementDtoConverter;
    }


    @Override
    public ResponseEntity<?> uploadImage(@NotNull MultipartFile file, @NotNull String attachementObj, @NotNull String extension) throws IOException {
        AttachementEntity attachementEntity = new AttachementEntity();
        attachementEntity.setNameAttachement(file.getName());
        if(!file.isEmpty()){
            attachementEntity.setAttachementBlob(file.getBytes());
            attachementEntity.setAttachementObj(attachementObj);
            attachementEntity.setExtension(extension);
            AttachementEntity attachement = attachementRepository.save(attachementEntity);
            AttachementDto attachementDto = attachementDtoConverter.convertToDto(attachement);
            return ResponseEntity.ok(attachementDto);
        }
        return ResponseEntity.badRequest().body("Faild to Upload File please try again");

    }


}
