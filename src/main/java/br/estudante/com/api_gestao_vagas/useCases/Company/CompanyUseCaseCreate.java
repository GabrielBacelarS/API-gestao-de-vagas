package br.estudante.com.api_gestao_vagas.useCases.Company;

import br.estudante.com.api_gestao_vagas.helprs.MessagerReturnDTO;
import br.estudante.com.api_gestao_vagas.modules.CompanyModel;
import br.estudante.com.api_gestao_vagas.repository.ComapanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CompanyUseCaseCreate {
    @Autowired
    ComapanyRepository comapanyRepository;

    public ResponseEntity<MessagerReturnDTO> executeCreate(CompanyModel companyModel) {
        CompanyModel companyExist = comapanyRepository.findByUsernameOrEmail(companyModel.getEmail(), companyModel.getEmail());
        if (companyExist == (null)) {
            comapanyRepository.save(companyModel);
            return ResponseEntity.status(201).body(new MessagerReturnDTO(true, "Empresa criada com sucesso! Bem vindo(a) " + companyModel.getBussinessName(), LocalDateTime.now()));
        } else {
            if (companyExist.getUsername().equals(companyModel.getUsername()) && companyExist.getEmail().equals(companyModel.getEmail())) {
                return ResponseEntity.status(409).body(new MessagerReturnDTO(false, "Username e Email ja cadastrados!", LocalDateTime.now()));
            } else if (companyExist.getUsername().equals(companyModel.getUsername())) {
                return ResponseEntity.status(409).body(new MessagerReturnDTO(false, "Username ja cadastrado", LocalDateTime.now()));
            } else {
                return ResponseEntity.status(409).body(new MessagerReturnDTO(false, "Email  ja cadastrado!", LocalDateTime.now()));
            }
        }
    }
}
