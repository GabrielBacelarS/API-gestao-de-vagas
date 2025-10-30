package br.estudante.com.api_gestao_vagas.controller;

import br.estudante.com.api_gestao_vagas.helprs.MessagerReturnDTO;
import br.estudante.com.api_gestao_vagas.modules.CandidateModel;
import br.estudante.com.api_gestao_vagas.useCases.Candidate.CandidateUseCaseCreate;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    @Autowired
    private CandidateUseCaseCreate candidateUseCaseCreate;

    @PostMapping("/create")
    public ResponseEntity<MessagerReturnDTO> create(@Valid @RequestBody CandidateModel candidate) {
         return candidateUseCaseCreate.executeCreate(candidate);
        }

    }

