package io.xmacedo.data.repository;

import io.xmacedo.data.domains.EStatusAccount;
import io.xmacedo.data.model.Account;
import io.xmacedo.exception.Exception;
import io.xmacedo.exception.ExceptionEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AccountJdbcRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public Account findAccountByCpf(String cpf) {
        String sql = "SELECT id, cpf,nome FROM  tb_contas" +
                " WHERE cpf = ?  AND status = ?";

        Object[] params = new Object[]{cpf, EStatusAccount.IDLE.getValue()};
        return this.jdbcTemplate.query(sql, params, rs -> {
            Account account = new Account();
            while (rs.next()) {
                account.setAccountId(rs.getInt("id"));
                account.setNome(rs.getString("nome"));
                account.setCpf(rs.getString("cpf"));
            }

            if (StringUtils.isBlank(account.getCpf())) {
                throw new Exception(ExceptionEnum.CPF_NOT_FOUND);
            }
            return account;
        });
    }
}
