package br.estudante.com.api_gestao_vagas.useCases.Candidate;

import br.estudante.com.api_gestao_vagas.dto.Candidate.UpdateCandidateDto;
import br.estudante.com.api_gestao_vagas.helprs.MessagerReturnDTO;
import br.estudante.com.api_gestao_vagas.modules.CandidateModel;
import br.estudante.com.api_gestao_vagas.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class CandidateUseCaseUpdate {
    @Autowired
    CandidateRepository candidateRepository;

    public ResponseEntity<MessagerReturnDTO> executeUpdate( UUID id, UpdateCandidateDto dto) {
        Optional<CandidateModel> candidateMatch = candidateRepository.findById(id);

        if (candidateMatch.isEmpty()) {
            return ResponseEntity.status(404)
                    .body(new MessagerReturnDTO(false, "Usuario não encontrado", LocalDateTime.now()));
        }

        CandidateModel candidate = candidateMatch.get();

        if (dto.getName() != null) candidate.setName(dto.getName());
        if (dto.getEmail() != null) candidate.setEmail(dto.getEmail());
        if (dto.getUsername() != null) candidate.setUsername(dto.getUsername());
        System.out.println(candidate);
        candidateRepository.save(candidate);

        return ResponseEntity.ok
                (new MessagerReturnDTO(true, "Sucesso, dados atualizados!", LocalDateTime.now())
        );
    }
}
