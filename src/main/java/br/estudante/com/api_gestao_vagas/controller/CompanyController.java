package br.estudante.com.api_gestao_vagas.controller;


import br.estudante.com.api_gestao_vagas.helprs.MessagerReturnDTO;
import br.estudante.com.api_gestao_vagas.modules.CompanyModel;
import br.estudante.com.api_gestao_vagas.useCases.Company.CompanyUseCaseCreate;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CompanyUseCaseCreate companyUseCaseCreate;

    @PostMapping("/create")
    public ResponseEntity<MessagerReturnDTO> crate(@Valid @RequestBody CompanyModel companyModel) {
       return companyUseCaseCreate.executeCreate(companyModel);
    }
}
