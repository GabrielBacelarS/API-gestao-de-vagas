package br.estudante.com.api_gestao_vagas.useCases.Candidate;

import br.estudante.com.api_gestao_vagas.helprs.MessagerReturnDTO;
import br.estudante.com.api_gestao_vagas.modules.CandidateModel;
import br.estudante.com.api_gestao_vagas.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CandidateUseCaseCreate {
    @Autowired
    private CandidateRepository candidateRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public ResponseEntity<MessagerReturnDTO> executeCreate(CandidateModel candidate) {
        CandidateModel userExist = candidateRepository.findByUsernameOrEmail(candidate.getUsername(), candidate.getEmail());
        if (userExist == (null)) {
            var password = passwordEncoder.encode(candidate.getPassword());
            candidate.setPassword(password);
            candidateRepository.save(candidate);
            return ResponseEntity.status(201).body(new MessagerReturnDTO(true, "Usuario criado com sucesso! Bem vindo(a) " + candidate.getName(),LocalDateTime.now()));
        } else {
            if (userExist.getUsername().equals(candidate.getUsername()) && userExist.getEmail().equals(candidate.getEmail())) {
                return ResponseEntity.status(409).body(new MessagerReturnDTO(false,"Username e Email ja cadastrados!", LocalDateTime.now()));
            } else if (userExist.getUsername().equals(candidate.getUsername())) {
                return ResponseEntity.status(409).body(new MessagerReturnDTO(false,"Username ja cadastrado", LocalDateTime.now()));
            } else {
                return ResponseEntity.status(409).body(new MessagerReturnDTO(false,"Email  ja cadastrado!", LocalDateTime.now()));
            }
        }
    }
}
