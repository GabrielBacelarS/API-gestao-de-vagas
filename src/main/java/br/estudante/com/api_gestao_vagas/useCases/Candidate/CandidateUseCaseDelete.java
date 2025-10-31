package br.estudante.com.api_gestao_vagas.useCases.Candidate;

import br.estudante.com.api_gestao_vagas.helprs.MessagerReturnDTO;
import br.estudante.com.api_gestao_vagas.modules.CandidateModel;
import br.estudante.com.api_gestao_vagas.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class CandidateUseCaseDelete {

    @Autowired
    CandidateRepository candidateRepository;

    public ResponseEntity<MessagerReturnDTO> executeDelete(String email, String password, UUID id){
        CandidateModel candidateMatch = candidateRepository.findByEmailAndPasswordAndId(email,password, id);
        if (candidateMatch == null){
            return ResponseEntity.status(401).body(new MessagerReturnDTO(false, "Email e senha não colidem", LocalDateTime.now()));
        }else {
            candidateRepository.delete(candidateMatch);
            return ResponseEntity.status(200).body(new MessagerReturnDTO(true, "Usuario deletado com sucesso" , LocalDateTime.now()));
        }

    }
}
