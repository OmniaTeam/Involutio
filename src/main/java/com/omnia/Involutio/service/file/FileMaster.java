package com.omnia.Involutio.service.file;

import com.omnia.Involutio.entity.FileEntity;
import com.omnia.Involutio.repository.FileRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class FileMaster {
    final private FileRepository fileRepository;

    public FileMaster(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
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
            String path = "/home/involutio/java/files/";
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
        if (fileEntity.isPresent()){
            String path = "/home/involutio/java/files/";
            return Paths.get(path + fileEntity.get().getName());

        }
        return null;
    }


}
