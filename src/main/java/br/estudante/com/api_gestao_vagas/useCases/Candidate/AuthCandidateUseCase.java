package br.estudante.com.api_gestao_vagas.useCases.Candidate;

import br.estudante.com.api_gestao_vagas.dto.Candidate.AuthCandidateDto;
import br.estudante.com.api_gestao_vagas.helprs.MessagerReturnDTO;
import br.estudante.com.api_gestao_vagas.repository.CandidateRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;

@Service
public class AuthCandidateUseCase {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    CandidateRepository candidateRepository;
    @Autowired
    @Value("${security.token.secret}")
    private String secretKey;

    public ResponseEntity<MessagerReturnDTO> executeAuth(AuthCandidateDto authCandidateDto) {
        var isExist = this.candidateRepository.findByUsername(authCandidateDto.getUsername()).orElse(null);

        if (isExist == null) {
            return ResponseEntity.status(401).body(new MessagerReturnDTO(false, "Senha ou usuario incorreto", LocalDateTime.now()));
        }

        var passwordMatch = this.passwordEncoder.matches(authCandidateDto.getPassword(), isExist.getPassword());
        if (!passwordMatch) {
            return ResponseEntity.status(401).body(new MessagerReturnDTO(false, "Senha ou usuario incorreto", LocalDateTime.now()));
        }
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        String token = JWT.create().withIssuer("api-gestao-vagas")
                .withSubject(isExist.getId().toString())
                .withClaim("roles", "CANDIDATE")
                .withExpiresAt(Instant.now().plus(Duration.ofHours(2)))
                .sign(algorithm);
        return ResponseEntity.status(200).body(new MessagerReturnDTO(true, token, LocalDateTime.now()));
    }
}
