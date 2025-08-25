package com.simple_official_spring_template.service.impl;

import com.simple_official_spring_template.service.FilesStorageService;
import com.simple_official_spring_template.utils.StringUtil;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Service
public class FilesStorageServiceImpl implements FilesStorageService {

  private final Path root = Paths.get("uploads");

  @Override
  public void init() {
    if (Files.exists(root) == false){
      try {
        Files.createDirectory(root);
      } catch (IOException e) {
        throw new RuntimeException("Could not initialize folder for upload!");
      }
    }
  }

  @Override
  public String save(MultipartFile file) {
    String fileName = null;
    String nakedFileName = "";
    String fileTyteName = "";
    try {

      fileName = StringUtil.unUnicodeAndRemoveSpace(file.getOriginalFilename(), "_", "._-");


      if (fileName.indexOf(".") != -1){
        String[] splitList = fileName.split("\\.");
        fileTyteName = "." + splitList[splitList.length - 1];
        nakedFileName = fileName.replaceAll(fileTyteName, "");
      }
      else {
        nakedFileName = fileName;
      }


      Integer count = 1;
      String addition = "";

      while (this.load(nakedFileName + addition + fileTyteName) != null){
        addition = "_" + count++;
      }
      Files.copy(file.getInputStream(), this.root.resolve(nakedFileName + addition + fileTyteName));
    } catch (Exception e) {
      System.out.println("Could not upload the file: " + file.getOriginalFilename() + "!");
    }

    return nakedFileName + fileTyteName;

  }

  @Override
  public Resource load(String filename) {
    try {
      Path file = root.resolve(filename);
      Resource resource = new UrlResource(file.toUri());

      if (resource.exists() || resource.isReadable()) {
        return resource;
      } else {
        return null;
      }
    } catch (MalformedURLException e) {
//      throw new RuntimeException("Error: " + e.getMessage());
      return null;
    }
  }

  @Override
  public Stream<Path> loadAll() {
    try {
      return Files.walk(this.root, 1).filter(path -> !path.equals(this.root)).map(this.root::relativize);
    } catch (IOException e) {
      throw new RuntimeException("Could not load the files!");
    }
  }

  @Override
  public void deleteFile(String filePath) {
    String fileName = filePath.split("/files/")[1];
    try {
      Files.delete(Paths.get("uploads/" + fileName));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
