package com.omnia.Involutio.service.file;

import com.omnia.Involutio.dto.CSVDTO;
import com.omnia.Involutio.entity.CSVEntity;
import com.omnia.Involutio.repository.CSVRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CSVReaderMaster {
    private final CSVRepository csvRepository;
    private final CSVComponent csvComponent;

    //TODO: mental magic
    public List<CSVEntity> read(String filePath) throws IOException {
        try {
            var list = csvComponent.parseCsv(filePath).stream()
                    .filter((csvdto) -> !csvRepository.existsByEmailIgnoreCaseAndDate(
                            csvdto.getEmail(),
                            LocalDate.now()))
                    .map(CSVEntity::new).toList();

            csvRepository.saveAll(list);
            return list;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }





}
