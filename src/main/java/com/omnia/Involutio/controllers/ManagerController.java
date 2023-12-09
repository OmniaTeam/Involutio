package com.omnia.Involutio.controllers;

import com.omnia.Involutio.service.ManagerMaster;
import com.omnia.Involutio.service.WorkerMaster;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/manager")
public class ManagerController {
    final private ManagerMaster managerMaster;
    final private WorkerMaster workerMaster;

    public ManagerController(ManagerMaster managerMaster, WorkerMaster workerMaster) {
        this.managerMaster = managerMaster;
        this.workerMaster = workerMaster;
    }

    @GetMapping("/all")
    ResponseEntity<?> getAll(){
        return ResponseEntity.ok(managerMaster.getAll());
    }
    @GetMapping("/{managerId}/workers")
    ResponseEntity<?> getWorkers(@PathVariable Long managerId){
        return ResponseEntity.ok(workerMaster.getAllwithManager(managerId));
    }
}
