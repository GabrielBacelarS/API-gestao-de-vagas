package br.estudante.com.api_gestao_vagas.modules;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.PrimitiveIterator;
import java.util.UUID;

@Data
@Entity(name = "candidate")
public class CandidateModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "O username não pode esta vazio, e não deve conter espacos")
    @Length(min = 5, max = 20, message =  "O username deve conter entre 5 a 20 strings")
    private String username;

    @NotBlank(message = "O name não pode esta vazio, e não deve conter espacos")
    @Length(min = 5, max = 20, message =  "O name deve conter entre 5 a 20 strings")
    private String name;

    @Email(message = "O email deve ser valido!")
    @NotBlank(message = "deve informar um email")
    private String email;

    @NotBlank(message = "A senha não pode esta vazia, e não deve conter espacos")
    @Length(min = 8, max = 20, message =  "A senha deve conter entre 8 a 20 strings")
    private String password;

    @Length(min = 8, max = 200, message =  "A descrição deve conter ate 200 charcters e ao menos 8")
    private String description;
    private String curriculum;

    @CreationTimestamp
    private LocalDateTime createdDate;
}
