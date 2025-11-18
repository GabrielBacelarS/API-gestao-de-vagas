package br.estudante.com.api_gestao_vagas.dto.Company;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
public class AuthCompanyDto {
    @Getter
    @NotBlank(message = "deve informar um Username")
    private String username;

    @Getter
    @NotBlank(message = "A senha não pode esta vazia, e não deve conter espacos")
    @Length(min = 8, max = 71, message =  "A senha deve conter mais que  8 characters")
    private String password;
}
