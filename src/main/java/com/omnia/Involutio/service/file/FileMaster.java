package com.omnia.Involutio.service.file;

import com.omnia.Involutio.entity.CSVEntity;
import com.omnia.Involutio.entity.FileEntity;
import com.omnia.Involutio.repository.FileRepository;
import com.omnia.Involutio.service.DataService;
import com.omnia.Involutio.service.ManagerRatingMaster;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
public class FileMaster {
    final private FileRepository fileRepository;
    final private CSVReaderMaster csvReaderMaster;
    final private DataService dataService;
    final private ManagerRatingMaster managerRatingMaster;

    final private String path = "/home/involutio/java/files/";

    public FileMaster(FileRepository fileRepository, CSVReaderMaster csvReaderMaster, DataService dataService, ManagerRatingMaster managerRatingMaster) {
        this.fileRepository = fileRepository;
        this.csvReaderMaster = csvReaderMaster;
        this.dataService = dataService;
        this.managerRatingMaster = managerRatingMaster;
    }

    public List<FileEntity> getAll(){
        return fileRepository.findAll();
    }

    public List<FileEntity> getAllPDF(){
        return fileRepository.findByTypeAndProcessedIsFalse("application/pdf");
    }

    public List<FileEntity> getAllCSV(){
        return fileRepository.findByTypeAndProcessedIsFalse("text/csv");
    }


    public FileEntity create(MultipartFile file, Long manager_id){
        if (!file.isEmpty()) {
            var name = LocalDate.now().toString() + "_" + file.getOriginalFilename();
            var type = file.getContentType();
            var fileEntity = new FileEntity(name, type, manager_id);
            try {
                File destFile = new File(path + name);
                file.transferTo(destFile);
                fileRepository.save(fileEntity);
                return fileEntity ;
            } catch (IOException e) {
                log.error(e.getMessage());
                e.printStackTrace();
            }
        }
        return null;
    }

    public Path download(Long fileId) {
        var fileEntity = fileRepository.findById(fileId);
        return fileEntity.map(entity -> Paths.get(path + entity.getName())).orElse(null);
    }

    public void uploadDataFromCSV() throws IOException {
        var files = getAllCSV();
        for (var i : files){
            List<CSVEntity> csvEntityList = csvReaderMaster.read(path + i.getName());
            i.setProcessed(true);
            fileRepository.save(i);
            csvEntityList.forEach(dataService::dataCsvProcessing);
            managerRatingMaster.updateManagerRating(i.getManager_id());
        }

    }


}
