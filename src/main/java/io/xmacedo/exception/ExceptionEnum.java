package io.xmacedo.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ExceptionEnum {

    // Ranges:
    // [00000-00999] - Uso interno
    // ====================================================
    // ===== Utilizar ranges de [000-999] por classe! =====
    // ====================================================
    // [010000-099999] - Uso clients

    // Uso interno

    ERROR_FEATURE_DISABLED(new Description("SPRING-00000", Origin.WS, Type.TEC, SubType.VALIDACAO, HttpStatus.BAD_REQUEST, "Esta funcionalidade não está disponível.", "")),
    TRANSACTION_ID_HEADER_IS_MISSING(new Description("SPRING-00001", Origin.WS, Type.TEC, SubType.VALIDACAO, HttpStatus.BAD_REQUEST, "O cabeçalho transactionId é obrigatório", "")),
    SECURITY_VALIDATE(new Description("SPRING-00002", Origin.WS, Type.TEC, SubType.VALIDACAO, HttpStatus.FORBIDDEN, "Operação não autorizada.", "")),
    CPF_NOT_FOUND(new Description("SPRING-00003", Origin.WS, Type.TEC, SubType.VALIDACAO, HttpStatus.NOT_FOUND, "Informação não localizada para este CPF.", "")),


    // Fields
    ;

    private final Description description;

    ExceptionEnum(Description description) {
        this.description = description;
    }

    @Data
    @AllArgsConstructor
    public static class Description {

        private String     code;
        private Origin     origin;
        private Type       type;
        private SubType    subtype;
        private HttpStatus status;
        private String     msg;
        private String     details;

    }

    public enum Origin {
        //clients
        WS
    }

    public enum Type {
        TEC, // Erro tecnológico (Integração etc)
        NEG  // Erro de negócio
    }


    public enum SubType {
        VALIDACAO,
        INTEGRACAO
    }

}
