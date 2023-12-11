package com.omnia.Involutio.controllers;

import com.omnia.Involutio.entity.UserEntity;
import com.omnia.Involutio.service.RatingMaster;
import com.omnia.Involutio.service.WorkerMaster;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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
    ResponseEntity<?> getWorker(@PathVariable Long workerId)
    {
        return ResponseEntity.ok(workerMaster.getWorker(workerId));
    }

    @GetMapping("/{workerId}/stat")
    ResponseEntity<?> getStatistic(@RequestParam ("start") LocalDate start, @RequestParam ("end") LocalDate end){
        return ResponseEntity.ok(ratingMaster.getStatistic(start,end));
    }

    @GetMapping("/userfromtoken")
    ResponseEntity<?> getUserFromToken(Authentication authentication){
        // по праву сильного можно обернуть: (UserEntity) authentication.getPrincipal()
        var user = (UserEntity) authentication.getPrincipal();
        System.out.println(user.getLogin());
        return ResponseEntity.ok("");
    }
}
