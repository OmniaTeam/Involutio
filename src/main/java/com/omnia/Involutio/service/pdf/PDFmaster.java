package com.omnia.Involutio.service.pdf;

import com.omnia.Involutio.entity.WorkerEntity;
import com.omnia.Involutio.service.ManagerMaster;
import com.omnia.Involutio.service.UserService;
import com.omnia.Involutio.service.WorkerMaster;
import com.omnia.Involutio.service.WorkerRatingMaster;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;


@Service
public class PDFmaster {
    final private WorkerMaster workerMaster;
    final private ManagerMaster managerMaster;
    final private WorkerRatingMaster workerRatingMaster;
    final private PDFBuilder pdfBuilder;
    final private UserService userService;

    public PDFmaster(WorkerMaster workerMaster, ManagerMaster managerMaster, WorkerRatingMaster workerRatingMaster, PDFBuilder pdfBuilder, UserService userService) {
        this.workerMaster = workerMaster;
        this.managerMaster = managerMaster;
        this.workerRatingMaster = workerRatingMaster;
        this.pdfBuilder = pdfBuilder;
        this.userService = userService;
    }

    public void createAllWorker(){
        var worker_list = workerMaster.getAll();
        for (var worker : worker_list){
            pdfBuilder.createPDF(worker.getId(), createContextwithWorker(worker));
        }
    }

    private Context createContextwithWorker(WorkerEntity worker){
        var manager = managerMaster.getWithManagerId(worker.getManagerId());
        var rating = workerRatingMaster.getLast7Days(worker.getId());
        var avg = 0;
        if(rating.size() > 0) {
            for (var i : rating) {
                avg += i.getRating();
            }
            avg = avg / rating.size();
        }
        var context = new Context();
        context.setVariable("departmentName", manager.getDepartment());
        context.setVariable("email", worker.getMail());
        context.setVariable("managerName", workerMaster.getLead(manager.getId()).getFIO());
        context.setVariable("curatorName", userService.getUserById(manager.getUserId()).getFio());
        context.setVariable("avg", avg);
        context.setVariable("actual", worker.getRating());
        return context;
    }
}
