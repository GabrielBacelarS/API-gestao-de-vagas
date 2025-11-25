package br.estudante.com.api_gestao_vagas.controller;

import br.estudante.com.api_gestao_vagas.dto.Jobs.CreateJobsDto;
import br.estudante.com.api_gestao_vagas.helprs.MessagerReturnDTO;
import br.estudante.com.api_gestao_vagas.modules.JobModel;
import br.estudante.com.api_gestao_vagas.useCases.Jobs.JobsUseCaseCreate;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/jobs")
public class JobsController {

    @Autowired
    private JobsUseCaseCreate jobsUseCaseCreate;


    @PostMapping("/create")
    public ResponseEntity<MessagerReturnDTO> create(@Valid @RequestBody CreateJobsDto modelDto, HttpServletRequest request) {
        var roles = request.getAttribute("roles");

        if (roles == null || !roles.toString().equals("COMPANY")) {
            return ResponseEntity.status(403).body(new MessagerReturnDTO(false, "Apenas empresas podem criar vagas", LocalDateTime.now()));
        }

        var userId = request.getAttribute("userId");

        var model = JobModel.builder()
                .level(modelDto.getLevel())
                .title(modelDto.getTitle())
                .description(modelDto.getDescription())
                .companyId(UUID.fromString(userId.toString()))
                .build();

        return jobsUseCaseCreate.executeCreate(model);
    }
}
