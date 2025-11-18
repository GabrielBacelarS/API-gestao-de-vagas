package br.estudante.com.api_gestao_vagas.dto.Candidate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

public class DeleteCandidateDto {

    @Getter
    @Email(message = "O email deve ser valido!")
    @NotBlank(message = "deve informar um email")
    private String email;

    @Getter
    @NotBlank(message = "A senha não pode esta vazia, e não deve conter espacos")
    @Length(min = 8, max = 71, message =  "A senha deve conter entre 8 a 20 strings")
    private String password;

}
