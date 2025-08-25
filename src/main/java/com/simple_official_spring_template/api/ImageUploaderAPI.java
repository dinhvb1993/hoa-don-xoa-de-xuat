package com.simple_official_spring_template.api;


import com.simple_official_spring_template.api.output.FileOutput;
import com.simple_official_spring_template.dto.FileInfo;
import com.simple_official_spring_template.service.FilesStorageCustomService;
import com.simple_official_spring_template.service.FilesStorageService;
import com.simple_official_spring_template.utils.PictureImgur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ImageUploaderAPI {

    @Autowired
    FilesStorageService storageService;

    @Autowired
    FilesStorageCustomService storageCustomService;


    @PostMapping(value = "/api-image-uploader/imgur")
    public FileOutput uploadImageImgur(@RequestBody MultipartFile file) throws IOException {
//        Path resourceDirectory = Paths.get("src","main","resources","public","src","upload");
//
////        File resourcesDirectory = new File("src/test/resources");
//
//        File convFile = new File(resourceDirectory + "/" + file.getOriginalFilename());
//        convFile.createNewFile();
//
//
//        FileOutputStream fos = new FileOutputStream(convFile);
//        fos.write(file.getBytes());
//        fos.close();
//
//        return PictureImgur.sendPictureToImgur(convFile);

        File convFile = new File(file.getOriginalFilename());
        convFile.createNewFile();


        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();

//        Files.deleteIfExists(Paths.get(convFile.getAbsolutePath()));

        return PictureImgur.sendPictureToImgur(convFile);
    }

    @PostMapping(value = "/api-image-uploader/simple")
    public FileOutput simpleUploadImage(@RequestBody MultipartFile file) throws IOException {
        FileOutput fileOutput = new FileOutput();
        try {
            String fileName = storageService.save(file);
            fileOutput.setLink(MvcUriComponentsBuilder
                    .fromMethodName(ImageUploaderAPI.class, "getFile", fileName).build().toString());
        } catch (Exception e) {
            System.out.println("Could not upload the file: " + file.getOriginalFilename() + "!");
        }
        return fileOutput;
    }


    @GetMapping("/files/{filename:.+}")
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        Resource file = storageService.load(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @GetMapping("/files")
    public ResponseEntity<List<FileInfo>> getListFiles() {
        List<FileInfo> fileInfos = storageService.loadAll().map(path -> {
            String filename = path.getFileName().toString();
            String url = MvcUriComponentsBuilder
                    .fromMethodName(ImageUploaderAPI.class, "getFile", path.getFileName().toString()).build().toString();

            return new FileInfo(filename, url);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
    }

    @GetMapping("/files2/{folder}")
    public ResponseEntity<List<FileInfo>> getListFiles2() {
        List<FileInfo> fileInfos = storageService.loadAll().map(path -> {
            String filename = path.getFileName().toString();
            String url = MvcUriComponentsBuilder
                    .fromMethodName(ImageUploaderAPI.class, "getFile", path.getFileName().toString()).build().toString();

            return new FileInfo(filename, url);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
    }


    @GetMapping("/api-file/delete")
    public void deleteFile(@RequestParam String filePath) {
        storageService.deleteFile(filePath);
    }


    //    CUSTOM FOLDER
    @GetMapping("/files-custom/{subpath}/{filename:.+}")
    public ResponseEntity<Resource> getFileCustom(
            @PathVariable String filename,
            @PathVariable String subpath // 2022-11-09
    ) {


        storageCustomService.setPath(subpath);

        storageCustomService.init();


        Resource file = storageCustomService.load(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }
}
