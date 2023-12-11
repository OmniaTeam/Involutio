package com.omnia.Involutio.service.file;

import com.omnia.Involutio.entity.FileEntity;
import com.omnia.Involutio.repository.FileRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
@Slf4j
public class FileMaster {
    final private FileRepository fileRepository;
    final private CSVReaderMaster csvReaderMaster;

    final private String path = "/home/involutio/java/files/";

    public FileMaster(FileRepository fileRepository, CSVReaderMaster csvReaderMaster) {
        this.fileRepository = fileRepository;
        this.csvReaderMaster = csvReaderMaster;
    }

    public List<FileEntity> getAll(){
        return fileRepository.findAll();
    }

    public FileEntity create(MultipartFile file){
        if (!file.isEmpty()) {
            var name = file.getName();
            var type = file.getContentType();
            var fileEntity = new FileEntity(name, type);
            fileRepository.save(fileEntity);
            //String path = "/home/involutio/java/files/";
            try {
                File destFile = new File(path + name);
                file.transferTo(destFile);
                return fileEntity ;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public Path download(Long fileId) {
        var fileEntity = fileRepository.findById(fileId);
        //String path = "/home/involutio/java/files/";
        return fileEntity.map(entity -> Paths.get(path + entity.getName())).orElse(null);
    }

    public void uploadDataFromCSV() throws IOException {
        var files = fileRepository.findByTypeAndProcessedIsFalse("csv");
        for (var i : files){
            csvReaderMaster.read(path + i.getName());
        }
    }


}
