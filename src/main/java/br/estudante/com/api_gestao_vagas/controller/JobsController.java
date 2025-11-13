package br.estudante.com.api_gestao_vagas.controller;

import br.estudante.com.api_gestao_vagas.helprs.MessagerReturnDTO;
import br.estudante.com.api_gestao_vagas.modules.JobModel;
import br.estudante.com.api_gestao_vagas.useCases.Jobs.JobsUseCaseCreate;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/jobs")
public class JobsController {

    @Autowired
    private JobsUseCaseCreate jobsUseCaseCreate;

    @PostMapping("/create")
    public ResponseEntity<MessagerReturnDTO> create(@Valid @RequestBody JobModel model){
        return jobsUseCaseCreate.executeCreate(model);
    }
}
