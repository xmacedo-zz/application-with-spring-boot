package io.xmacedo.controller;

import io.xmacedo.data.model.Account;
import io.xmacedo.data.rest.request.AccountRequest;
import io.xmacedo.exception.Exception;
import io.xmacedo.exception.ExceptionEnum;
import io.xmacedo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/operation")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Value("${op_enable_example}")
    private boolean enableOpExample;


    @PutMapping(
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity<Account> find(@RequestBody @Valid AccountRequest request) {
        if(enableOpExample)
            return this.accountService.find(request);

        throw new Exception(ExceptionEnum.ERROR_FEATURE_DISABLED);
    }


}
