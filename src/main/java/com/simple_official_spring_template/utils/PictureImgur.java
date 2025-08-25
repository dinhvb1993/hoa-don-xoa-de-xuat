package com.simple_official_spring_template.utils;

import com.simple_official_spring_template.api.output.FileOutput;
import com.simple_official_spring_template.api.output.PictureImgurOutput;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collections;

public class PictureImgur {
//    public static String sendPictureToImgur(String avatar){
////        List<String> listImgur = new ArrayList<>();
//        String uri = "https://api.imgur.com/3/upload";
//        RestTemplate restTemplate = new RestTemplate();
//        HttpHeaders headers = new HttpHeaders();
//        headers.setBearerAuth("975814c469fd797ac13917b8cd36506067ba9ded");
//        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
//        HttpEntity<String> entity = new HttpEntity<>(avatar, headers);
//
//        ResponseEntity<ResponseImgur> response = restTemplate.exchange(uri, HttpMethod.POST, entity, ResponseImgur.class);
//        return response.getBody().getData().getLink();
//    }

    public static FileOutput sendPictureToImgur(File file) throws IOException {
        FileOutput fileOutput = new FileOutput();
        byte[] bytesArray = new byte[(int) file.length()];

        FileInputStream fis = new FileInputStream(file);
        fis.read(bytesArray); //read file into bytes[]
        fis.close();
//        List<String> listImgur = new ArrayList<>();
        String uri = "https://api.imgur.com/3/image";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth("c31226f112ac397aa41cee07f81486a768a60049");
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));



        HttpEntity<byte[]> requestBody = new HttpEntity<>(bytesArray, headers);

        ResponseEntity<PictureImgurOutput> response
                = restTemplate.postForEntity(uri, requestBody, PictureImgurOutput.class);

//        ResponseEntity<PictureImgurOutput> response = restTemplate.exchange(uri, HttpMethod.POST, requestBody, PictureImgurOutput.class);

        file.delete();
        fileOutput.setLink(response.getBody().getData().getLink());
        return fileOutput;

    }



}
