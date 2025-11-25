package br.estudante.com.api_gestao_vagas.modules;

import br.estudante.com.api_gestao_vagas.helprs.Enums.LevelsJob;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity(name = "job")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JobModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "O título da vaga não pode estar vazio")
    private String title;

    @NotBlank(message = "A empresa deve inserir uma descrição para a vaga")
    private String description;
    private String benefits;

    @NotNull(message = "Levls job não pode esta vazio")
    private LevelsJob level;

    @ManyToOne
    @JoinColumn(name = "companyId", insertable = false, updatable = false)
    private CompanyModel companyModel;


    private UUID companyId;
    @CreationTimestamp
    private LocalDateTime createdDate;
}
