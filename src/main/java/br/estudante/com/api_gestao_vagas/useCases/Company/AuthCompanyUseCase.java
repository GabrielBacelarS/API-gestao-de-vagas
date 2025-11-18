package br.estudante.com.api_gestao_vagas.useCases.Company;


import br.estudante.com.api_gestao_vagas.dto.Company.AuthCompanyDto;
import br.estudante.com.api_gestao_vagas.helprs.MessagerReturnDTO;
import br.estudante.com.api_gestao_vagas.repository.ComapanyRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuthCompanyUseCase {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    ComapanyRepository comapanyRepository;
    @Value("${security.token.secret}")
    private String secretKey;

    public ResponseEntity<MessagerReturnDTO> executeAuth(AuthCompanyDto authCompanyDto){
        var isExist = this.comapanyRepository.findByUsername(authCompanyDto.getUsername()).orElseThrow(
                () ->    {
                throw new UsernameNotFoundException("Company not Found");
        });

        var passwordMatch = this.passwordEncoder.matches(authCompanyDto.getPassword(), isExist.getPassword());

        if (!passwordMatch){
            return  ResponseEntity.status(401).body(new MessagerReturnDTO(false,"Senha ou usuario incorreto", LocalDateTime.now()));
        }
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        String token = JWT.create().withIssuer("api-gestao-vagas")
                .withSubject(isExist.getId().toString())
                .sign(algorithm);
                return  ResponseEntity.status(200).body(new MessagerReturnDTO(true,token,LocalDateTime.now()));

    }
}
