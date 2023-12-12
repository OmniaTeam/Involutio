package com.omnia.Involutio.service;

import com.omnia.Involutio.service.file.FileMaster;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.Schedules;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Slf4j
public class SchedulerService {
    final private FileMaster fileMaster;

    public SchedulerService(FileMaster fileMaster) {
        this.fileMaster = fileMaster;
    }

    @Scheduled(cron = "0 0/5 * * * *")
    public void uploadCSV() throws IOException {
        try {
        fileMaster.uploadDataFromCSV();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Scheduled(cron = "0 0/10 * * * *")
    public void analys(){
        //анализа данных
        System.out.print("analusis");
    }

}
