package com.simple_official_spring_template.service.impl;

import com.simple_official_spring_template.service.IFileProcessing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileProcessing implements IFileProcessing {

    @Autowired
    private ResourceLoader resourceLoader;


    @Override
    public void processCsvFile(MultipartFile file) throws IOException {






        File convFile = new File(file.getOriginalFilename());
        convFile.createNewFile();



        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();


        FileInputStream is = null;
        try {
            is = new FileInputStream(convFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }




        BufferedReader fileReader = null;
        try {
            fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }



        CSVParser csvParser = null;
        try {
            csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();




            for (CSVRecord csvRecord : csvRecords) {
                for (String header: csvParser.getHeaderNames()){
                    System.out.println(header + ":" + csvRecord.get(header));
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        is.close();


        Files.deleteIfExists(Paths.get(convFile.getAbsolutePath()));


    }
}
