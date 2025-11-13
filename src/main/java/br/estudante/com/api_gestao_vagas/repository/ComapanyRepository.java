package br.estudante.com.api_gestao_vagas.repository;

import br.estudante.com.api_gestao_vagas.modules.CompanyModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ComapanyRepository extends JpaRepository<CompanyModel, UUID> {
    CompanyModel findByUsernameOrEmail(String userName, String email);
    Optional<CompanyModel> findById(UUID id);
}
