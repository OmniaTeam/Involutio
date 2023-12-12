package com.omnia.Involutio.service.file;

import com.omnia.Involutio.dto.CSVDTO;
import com.omnia.Involutio.entity.CSVEntity;
import com.omnia.Involutio.repository.CSVRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class CSVReaderMaster {
    private final CSVRepository csvRepository;
    private final CSVComponent csvComponent;

    public CSVReaderMaster(CSVRepository csvRepository, CSVComponent csvComponent) {
        this.csvRepository = csvRepository;
        this.csvComponent = csvComponent;
    }
    //TODO: mental magic
    public List<CSVDTO> read(String filePath) throws IOException {
        try {
            var list = csvComponent.parseCsv(filePath);
            for (var i : list){
                csvRepository.save(new CSVEntity(i));
            }
            return list;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }





}
