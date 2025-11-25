package br.estudante.com.api_gestao_vagas.dto.Candidate;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

public class AuthCandidateDto {
    @Getter
    @NotBlank(message = "deve informar um Username")
    private String username;

    @Getter
    @NotBlank(message = "A senha não pode esta vazia, e não deve conter espacos")
    @Length(min = 8, max = 71, message =  "A senha deve conter mais que  8 characters")
    private String password;
}
