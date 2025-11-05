package br.estudante.com.api_gestao_vagas.modules;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity(name = "job")
public class JobModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String description;
    private String benefits;
    private String level;

    @ManyToOne
    @JoinColumn(name = "companyId", insertable = false, updatable = false)
    private CompanyModel companyModel;

    private UUID companyId;
    @CreationTimestamp
    private LocalDateTime createdDate;
}
