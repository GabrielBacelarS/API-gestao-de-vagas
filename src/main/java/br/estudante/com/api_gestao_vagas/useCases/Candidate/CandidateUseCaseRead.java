package br.estudante.com.api_gestao_vagas.useCases.Candidate;

import br.estudante.com.api_gestao_vagas.helprs.MessagerReturnDTO;
import br.estudante.com.api_gestao_vagas.modules.CandidateModel;
import br.estudante.com.api_gestao_vagas.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CandidateUseCaseRead {
    @Autowired
    CandidateRepository candidateRepository;

    public ResponseEntity<MessagerReturnDTO> executeRead(UUID id){
        Optional<CandidateModel> idMath = candidateRepository.findById(id);
        if (idMath.isEmpty()){
            return ResponseEntity.status(404).body(new MessagerReturnDTO(false,"Id não valido ou Usuario não existe", LocalDateTime.now()));
        }else {
            CandidateModel candidate = idMath.get();
            String userInfo = "Nome: " + candidate.getName() + " | Email: " + candidate.getEmail() + " | Username: " + candidate.getUsername();
            return ResponseEntity.status(200).body(new MessagerReturnDTO(true,userInfo,LocalDateTime.now()));
        }
    }
}
