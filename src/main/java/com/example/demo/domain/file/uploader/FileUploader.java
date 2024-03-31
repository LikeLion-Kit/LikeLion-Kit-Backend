package com.example.demo.domain.file.uploader;

import com.example.demo.domain.board.domain.Board;
import com.example.demo.domain.file.domain.FileNameInfo;
import com.example.demo.domain.file.domain.entity.UploadFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface FileUploader {
    List<FileNameInfo> storeFiles(List<MultipartFile> multipartFiles,  Object saved) throws IOException;

    FileNameInfo storeFile(MultipartFile multipartFile, Object saved) throws IOException;
    void deletePostFiles(Board board);
    void deleteFile(String storeFileName);
    List<FileNameInfo> getPostFiles(Board board);

    FileNameInfo getPostAttachFile(List<UploadFile> uploadFiles);
    List<FileNameInfo> getPostImagesFiles(List<UploadFile> uploadFiles);

    default String createStoreFileName(String originalFilename) {
        String ext = extractExt(originalFilename);
        String uuid = UUID.randomUUID().toString();
        return uuid + "." + ext;
    }
    default String extractExt(String originalFilename) {
        int pos = originalFilename.lastIndexOf(".");
        return originalFilename.substring(pos + 1);
    }
}
