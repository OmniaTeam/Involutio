package com.omnia.Involutio.service.pdf;

import com.omnia.Involutio.service.ManagerMaster;
import com.omnia.Involutio.service.WorkerMaster;
import com.omnia.Involutio.service.WorkerRatingMaster;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

@Service
public class PDFcontextLoader {
    final private WorkerMaster workerMaster;
    final private ManagerMaster managerMaster;
    final private WorkerRatingMaster workerRatingMaster;

    public PDFcontextLoader(WorkerMaster workerMaster, ManagerMaster managerMaster, WorkerRatingMaster workerRatingMaster) {
        this.workerMaster = workerMaster;
        this.managerMaster = managerMaster;
        this.workerRatingMaster = workerRatingMaster;
    }

    public Context createContextwithWorker(Long workerId){
        var worker = workerMaster.getWorker(workerId);
        var manager = managerMaster.getWithManagerId(worker.getManagerId());
        var rating = workerRatingMaster.getLast7Days(workerId);
        var context = new Context();
        return context;
    }
}
