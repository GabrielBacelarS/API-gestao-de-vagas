package br.estudante.com.api_gestao_vagas.useCases.Jobs;

import br.estudante.com.api_gestao_vagas.dto.Jobs.CreateJobsDto;
import br.estudante.com.api_gestao_vagas.helprs.MessagerReturnDTO;
import br.estudante.com.api_gestao_vagas.modules.JobModel;
import br.estudante.com.api_gestao_vagas.repository.ComapanyRepository;
import br.estudante.com.api_gestao_vagas.repository.JobsRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class JobsUseCaseCreate {
    @Autowired
    ComapanyRepository comapanyRepository;
    @Autowired
    JobsRepository jobsRepository;

    public ResponseEntity<MessagerReturnDTO> executeCreate(@Valid JobModel model) {
        boolean companyExist = comapanyRepository.findById(model.getCompanyId()).isEmpty();
        if (companyExist == true) {
            return ResponseEntity.status(404).body(new MessagerReturnDTO(false, "Empresa não encontrada", LocalDateTime.now()));
        } else {
            jobsRepository.save(model);
            return ResponseEntity.status(200).body(new MessagerReturnDTO(true, "Vaga criada com sucesso:  " + model.getTitle(), LocalDateTime.now()));
        }
    }
}
