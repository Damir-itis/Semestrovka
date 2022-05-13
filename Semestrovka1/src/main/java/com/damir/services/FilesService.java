package com.damir.services;

import com.damir.models.FileInfo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface FilesService {
    FileInfo upload(MultipartFile multipart);
    List<FileInfo> upload(MultipartFile[] multipart);

    void addFileToResponse(String fileName, HttpServletResponse response);
}
