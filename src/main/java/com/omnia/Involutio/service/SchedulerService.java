package com.omnia.Involutio.service;

import com.omnia.Involutio.service.file.FileMaster;
import com.omnia.Involutio.service.pdf.PDFmaster;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Slf4j
public class SchedulerService {
    final private FileMaster fileMaster;
    final private PDFmaster pdfMaster;

    public SchedulerService(FileMaster fileMaster, PDFmaster pdfMaster) {
        this.fileMaster = fileMaster;
        this.pdfMaster = pdfMaster;
    }

    @Scheduled(cron = "0 * * * * *")
    public void uploadCSV() throws IOException {
        try {
            fileMaster.uploadDataFromCSV();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Scheduled(cron = "0 0 1 * * *")
    public void pdf() {
        pdfMaster.createAllWorker();
    }


}
