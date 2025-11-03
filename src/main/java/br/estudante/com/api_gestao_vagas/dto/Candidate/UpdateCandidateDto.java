package br.estudante.com.api_gestao_vagas.dto.Candidate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;
@Data
public class UpdateCandidateDto {


    @NotBlank(message = "O username não pode esta vazio, e não deve conter espacos")
    @Length(min = 5, max = 20, message =  "O username deve conter entre 5 a 20 strings")
    private String username;


    @NotBlank(message = "O name não pode esta vazio, e não deve conter espacos")
    @Length(min = 5, max = 20, message =  "O name deve conter entre 5 a 20 strings")
    private String name;


    @Email(message = "O email deve ser valido!")
    @NotBlank(message = "deve informar um email")
    private String email;
}
