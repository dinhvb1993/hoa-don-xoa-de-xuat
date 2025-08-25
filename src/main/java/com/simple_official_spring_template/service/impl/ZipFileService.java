package com.simple_official_spring_template.service.impl;

import com.simple_official_spring_template.converter.CityConverter;
import com.simple_official_spring_template.dto.CityDTO;
import com.simple_official_spring_template.entity.CityEntity;
import com.simple_official_spring_template.repository.ICityRepository;
import com.simple_official_spring_template.service.ICityService;
import com.simple_official_spring_template.service.IZipFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
@Transactional(rollbackFor = Exception.class)
public class ZipFileService implements IZipFileService {


    @Override
    public String zipMultipleFile(List<String> urls) {

        String zipFilename = "invoice.zip";

        String fullPathName = "uploads/" + zipFilename;

                try
        {

            //Zipped file
//            String zipFilename = "uploads/invoice.zip";
            File zipFile = new File(fullPathName);
            FileOutputStream fos  = new FileOutputStream(zipFile);
            ZipOutputStream zos = new ZipOutputStream(fos);

            for (String url: urls){
//                String fileName1 = "uploads/2023/03/09/news-2.jpg";
//                zipFile(fileName1, zos);
                zipFile(url, zos);
            }


            zos.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return zipFilename;
    }


    private static void zipFile(String fileName, ZipOutputStream zos) throws IOException
    {
        final int BUFFER = 1024;
        BufferedInputStream bis = null;
        try
        {

            File file;
            if (!fileName.contains("http")){
                file = new File(fileName);
            }
            else {
                URL url1=new URL(fileName);
                file = new File(url1.getFile());
            }


            FileInputStream fis = new FileInputStream(file);
            bis = new BufferedInputStream(fis, BUFFER);

            // ZipEntry --- Here file name can be created using the source file
            ZipEntry zipEntry = new ZipEntry(file.getName());
            zos.putNextEntry(zipEntry);
            byte data[] = new byte[BUFFER];
            int count;
            while((count = bis.read(data, 0, BUFFER)) != -1)
            {
                zos.write(data, 0, count);
            }
            // close entry every time
            zos.closeEntry();
        }
        finally
        {
            try
            {
                bis.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}
