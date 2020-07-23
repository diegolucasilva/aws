package br.com.lirio.appredis.contrato.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.index.Indexed;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Jornada {

    @Indexed
    private Long numero;
    private LocalDateTime data; 


}
