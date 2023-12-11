package com.omnia.Involutio.controllers;

import com.omnia.Involutio.entity.ManagerEntity;
import com.omnia.Involutio.entity.WorkerEntity;
import com.omnia.Involutio.service.ManagerMaster;
import com.omnia.Involutio.service.WorkerMaster;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/manager")
@Slf4j
public class ManagerController {
    final private ManagerMaster managerMaster;
    final private WorkerMaster workerMaster;

    public ManagerController(ManagerMaster managerMaster, WorkerMaster workerMaster) {
        this.managerMaster = managerMaster;
        this.workerMaster = workerMaster;
    }

    @GetMapping("/all")
    ResponseEntity<List<ManagerEntity>> getAll(){
        return ResponseEntity.ok(managerMaster.getAll());
    }

    @GetMapping("/{userId}")
    ResponseEntity<ManagerEntity> getManager(@PathVariable Long userId){
        return ResponseEntity.ok(managerMaster.getWithUser(userId));
    }

    @GetMapping("/{managerId}/workers")
    ResponseEntity<List<WorkerEntity>> getWorkers(@PathVariable Long managerId){
        return ResponseEntity.ok(workerMaster.getAllWithManager(managerId));
    }
}
