package com.omnia.Involutio.controllers;

import com.omnia.Involutio.entity.WorkerEntity;
import com.omnia.Involutio.service.WorkerMaster;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/worker")
@Slf4j
public class WorkerController {
    final private WorkerMaster workerMaster;
    public WorkerController(WorkerMaster workerMaster) {
        this.workerMaster = workerMaster;
    }

    @GetMapping("/{workerId}")
    ResponseEntity<?> getUser(@PathVariable Long workerId){

        return ResponseEntity.ok(new WorkerEntity());
    }
}
