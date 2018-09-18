package io.xmacedo.utils;

import io.xmacedo.utils.enums.ERequestCallerType;
import lombok.Data;

@Data
public class RequestMetaData {
    private String requestPath;
    private String requestUri;
    private String transactionId;
    private ERequestCallerType callerType;
    private String callerId;
    private String apiKey;
    private String token;
    private String company;
    private String checkingAccount;
    private String savingAccount;
    private String coopAccount;

    public RequestMetaData() {
        this.requestUri = "";
        this.transactionId = "";
        this.callerType = ERequestCallerType.SISTEMA;
        this.callerId = "";
    }

}
