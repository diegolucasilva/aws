package br.com.lirio.appredis.contrato.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.index.Indexed;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Contrato {

    @Indexed
    private String idContrato;
    private String codigo;
    private Double valor;

}
