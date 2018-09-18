package io.xmacedo.data.model;

import io.xmacedo.data.domains.EStatusAccount;
import lombok.Data;

@Data
public class Account {

    private Integer accountId;
    private String nome;
    private EStatusAccount status;
    private String cpf;
}
