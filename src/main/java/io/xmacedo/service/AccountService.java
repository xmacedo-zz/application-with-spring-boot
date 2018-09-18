package io.xmacedo.service;

import io.xmacedo.data.model.Account;
import io.xmacedo.data.repository.AccountJdbcRepository;
import io.xmacedo.data.rest.request.AccountRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AccountService {

    @Autowired
    private AccountJdbcRepository accountJdbcRepository;

    public ResponseEntity<Account> find(AccountRequest request){
        return new ResponseEntity<>(this.accountJdbcRepository.findAccountByCpf(request.getCpf()),HttpStatus.OK);
    }
}
