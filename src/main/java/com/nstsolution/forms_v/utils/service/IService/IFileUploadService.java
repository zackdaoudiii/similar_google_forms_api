package com.nstsolution.forms_v.utils.service.IService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.io.IOException;

public interface IFileUploadService {

    ResponseEntity<?> uploadImage(@NotNull MultipartFile file, @NotNull String attachementObj, @NotNull String extension) throws IOException;
}
