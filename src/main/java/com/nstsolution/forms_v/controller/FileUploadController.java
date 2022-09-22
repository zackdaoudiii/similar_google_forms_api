package com.nstsolution.forms_v.controller;

import com.nstsolution.forms_v.utils.service.IService.IFileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("api/files/")
public class FileUploadController {

    private final IFileUploadService iFileUploadService;


    @Autowired
    public FileUploadController(IFileUploadService iFileUploadService) {
        this.iFileUploadService = iFileUploadService;
    }


    @PostMapping
    public ResponseEntity<?> saveFile(@RequestParam("file") MultipartFile attachement,
                                      @RequestParam("attachementObj") String attachementObj,
                                      @RequestParam("extension") String extension) throws IOException {
        return iFileUploadService.uploadImage(attachement ,attachementObj,extension);
    }


}
