package br.estudante.com.api_gestao_vagas.repository;

import br.estudante.com.api_gestao_vagas.modules.CandidateModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CandidateRepository extends JpaRepository<CandidateModel, UUID> {
    CandidateModel findByUsernameOrEmail(String userName, String email);
    CandidateModel findByEmailAndPasswordAndId(String email, String password, UUID id);
    Optional<CandidateModel> findById(UUID id);
}
