package br.estudante.com.api_gestao_vagas.repository;

import br.estudante.com.api_gestao_vagas.modules.JobModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JobsRepository extends JpaRepository<JobModel, UUID> {
  //  JobModel FindByTitle(String tile);
}
