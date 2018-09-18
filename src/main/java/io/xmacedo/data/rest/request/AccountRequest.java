package io.xmacedo.data.rest.request;

import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

@Data
public class AccountRequest {
    @CPF
    private String cpf;
}
