//package com.simple_official_spring_template.api;
//
//import com.simple_official_spring_template.service.FilesStorageService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.io.Resource;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//
//
//@Controller
////@CrossOrigin("http://localhost:8081")
//public class FilesController {
//
//  @Autowired
//  FilesStorageService storageService;
//
//  @GetMapping("/files/{filename:.+}")
//  public ResponseEntity<Resource> getFile(@PathVariable String filename) {
//    Resource file = storageService.load(filename);
//    return ResponseEntity.ok()
//        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
//  }
//}
