package br.estudante.com.api_gestao_vagas.controller;

import br.estudante.com.api_gestao_vagas.dto.Candidate.DeleteCandidateDto;
import br.estudante.com.api_gestao_vagas.dto.Candidate.UpdateCandidateDto;
import br.estudante.com.api_gestao_vagas.helprs.MessagerReturnDTO;
import br.estudante.com.api_gestao_vagas.modules.CandidateModel;
import br.estudante.com.api_gestao_vagas.useCases.Candidate.CandidateUseCaseCreate;
import br.estudante.com.api_gestao_vagas.useCases.Candidate.CandidateUseCaseDelete;
import br.estudante.com.api_gestao_vagas.useCases.Candidate.CandidateUseCaseRead;
import br.estudante.com.api_gestao_vagas.useCases.Candidate.CandidateUseCaseUpdate;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    @Autowired
    private CandidateUseCaseCreate candidateUseCaseCreate;
    @Autowired
    private CandidateUseCaseDelete candidateUseCaseDelete;
    @Autowired
    private CandidateUseCaseRead candidateUseCaseRead;
    @Autowired
    private CandidateUseCaseUpdate candidateUseCaseUpdate;

    @PostMapping("/create")
    public ResponseEntity<MessagerReturnDTO> create(@Valid @RequestBody CandidateModel candidate) {
        return candidateUseCaseCreate.executeCreate(candidate);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<MessagerReturnDTO> update(@Valid @RequestBody DeleteCandidateDto candidateDto, @RequestParam(name = "id", required = true) UUID id){
        return  candidateUseCaseDelete.executeDelete(candidateDto.getEmail(),candidateDto.getPassword(), id);
    }

    @GetMapping("/read")
    public  ResponseEntity<MessagerReturnDTO> read(@Valid @RequestParam(name = "id",  required = true) UUID id){
        return candidateUseCaseRead.executeRead(id);
    }
    @PutMapping("/update")
    public ResponseEntity<MessagerReturnDTO> update(@Valid @RequestParam(name = "id", required = true) UUID id,@RequestBody UpdateCandidateDto updateCandidateDto){
        return  candidateUseCaseUpdate.executeUpdate(id, updateCandidateDto);
    }
}

