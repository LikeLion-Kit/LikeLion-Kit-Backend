package com.example.demo.domain.file.repository;

import com.example.demo.domain.file.domain.entity.UploadFile;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FileRepository extends JpaRepository<UploadFile, Long> {
}
