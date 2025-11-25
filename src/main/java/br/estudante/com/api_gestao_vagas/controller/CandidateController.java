package br.estudante.com.api_gestao_vagas.controller;

import br.estudante.com.api_gestao_vagas.dto.Candidate.AuthCandidateDto;
import br.estudante.com.api_gestao_vagas.dto.Candidate.DeleteCandidateDto;
import br.estudante.com.api_gestao_vagas.dto.Candidate.UpdateCandidateDto;
import br.estudante.com.api_gestao_vagas.helprs.MessagerReturnDTO;
import br.estudante.com.api_gestao_vagas.modules.CandidateModel;
import br.estudante.com.api_gestao_vagas.useCases.Candidate.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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
    @Autowired
    private AuthCandidateUseCase authCandidateUseCase;

    @PostMapping("/create")
    public ResponseEntity<MessagerReturnDTO> create(@Valid @RequestBody CandidateModel candidate) {
        return candidateUseCaseCreate.executeCreate(candidate);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<MessagerReturnDTO> delete(@Valid @RequestBody DeleteCandidateDto candidateDto, HttpServletRequest request) {
        var roles = request.getAttribute("roles");

        if (roles == null || !roles.toString().equals("CANDIDATE")) {
            return ResponseEntity.status(403).body(new MessagerReturnDTO(false, "Apenas candidatos podem acessar esta rota", LocalDateTime.now()));
        }

        var userId = request.getAttribute("userId");
        UUID candidateId = UUID.fromString(userId.toString());

        return candidateUseCaseDelete.executeDelete(candidateDto.getEmail(), candidateDto.getPassword(), candidateId);
    }

    @GetMapping("/read")
    public ResponseEntity<MessagerReturnDTO> read(HttpServletRequest request) {
        var roles = request.getAttribute("roles");

        if (roles == null || !roles.toString().equals("CANDIDATE")) {
            return ResponseEntity.status(403).body(new MessagerReturnDTO(false, "Apenas candidatos podem acessar esta rota", LocalDateTime.now()));
        }

        var userId = request.getAttribute("userId");
        UUID candidateId = UUID.fromString(userId.toString());

        return candidateUseCaseRead.executeRead(candidateId);
    }
    @PutMapping("/update")
    public ResponseEntity<MessagerReturnDTO> update(@Valid @RequestBody UpdateCandidateDto updateCandidateDto, HttpServletRequest request) {
        var roles = request.getAttribute("roles");

        if (roles == null || !roles.toString().equals("CANDIDATE")) {
            return ResponseEntity.status(403).body(new MessagerReturnDTO(false, "Apenas candidatos podem acessar esta rota", LocalDateTime.now()));
        }

        var userId = request.getAttribute("userId");
        UUID candidateId = UUID.fromString(userId.toString());

        return candidateUseCaseUpdate.executeUpdate(candidateId, updateCandidateDto);
    }

    @PostMapping("/auth")
    public ResponseEntity<MessagerReturnDTO> auth(@Valid @RequestBody AuthCandidateDto authCandidateDto){
        return authCandidateUseCase.executeAuth(authCandidateDto);
    }
}

