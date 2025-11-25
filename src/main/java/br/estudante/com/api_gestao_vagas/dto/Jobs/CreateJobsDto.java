package br.estudante.com.api_gestao_vagas.dto.Jobs;

import br.estudante.com.api_gestao_vagas.helprs.Enums.LevelsJob;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateJobsDto {
    @NotBlank(message = "O título da vaga não pode estar vazio")
    private String title;

    @NotBlank(message = "A empresa deve inserir uma descrição para a vaga")
    private String description;


    @NotNull(message = "Levls job não pode esta vazio")
    private LevelsJob level;
}
