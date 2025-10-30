package br.estudante.com.api_gestao_vagas.helprs;

import java.time.LocalDateTime;

public record MessagerReturnDTO(Boolean sucess, String message, LocalDateTime date ) {

}
