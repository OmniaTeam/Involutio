package com.omnia.Involutio.controllers;

import com.omnia.Involutio.entity.WorkerEntity;
import com.omnia.Involutio.service.RatingMaster;
import com.omnia.Involutio.service.WorkerMaster;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/worker")
@Slf4j
public class WorkerController {
    final private WorkerMaster workerMaster;
    final private RatingMaster ratingMaster;
    public WorkerController(WorkerMaster workerMaster, RatingMaster ratingMaster) {
        this.workerMaster = workerMaster;
        this.ratingMaster = ratingMaster;
    }

    @GetMapping("/{workerId}")
    ResponseEntity<?> getUser(@PathVariable Long workerId){
        return ResponseEntity.ok(new WorkerEntity());
    }

    @GetMapping("/{workerId}/stat")
    ResponseEntity<?> getStatistic(@RequestParam ("start") LocalDate start, @RequestParam ("end") LocalDate end){
        return ResponseEntity.ok(ratingMaster.getStatistic(start,end));
    }

}
