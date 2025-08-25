package com.simple_official_spring_template.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface FilesStorageService {
  void init();
  String save(MultipartFile file);
  Resource load(String filename);
  Stream<Path> loadAll();
  void deleteFile(String filePath);
}
