package com.omnia.Involutio.service.file;

import com.omnia.Involutio.dto.CSVDTO;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;


@Component
public class CSVComponent {
    //TODO: mental magic
    public List<CSVDTO> parseCsv(String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            CsvToBean<CSVDTO> csvToBean = new CsvToBeanBuilder<CSVDTO>(reader)
                    .withType(CSVDTO.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .withSeparator(';')
                    .build();

            return csvToBean.parse();
        }
    }
}
