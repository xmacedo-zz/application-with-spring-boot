package io.xmacedo.client.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.xmacedo.context.Context;
import io.xmacedo.context.ContextHolder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper=false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Response<T> extends JsonAnyProperties implements Cloneable, Serializable {

    private static final long serialVersionUID = -4395433642534847121L;

    private Meta  meta;
    private T     data;
    private Error error;

    @Override
    public Response<T> clone() {
        Response<T> response = new Response<>();

        response.other.putAll(other);

        response.meta = meta.clone();
        response.error = error.clone();

        if (data != null) {
            try {
                response.data = (T) data.getClass().getMethod("clone").invoke(data);
            } catch (Exception e) {
                response.data = data;
            }
        }

        return response;
    }

    @Data
    @EqualsAndHashCode(callSuper=false)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Meta extends JsonAnyProperties implements Cloneable, Serializable {

        private static final long serialVersionUID = -6159160859985624035L;

        private String transactionId;
        private String processName;
        private String status;

        public static Meta fromContext(String status) {
            Meta    meta    = new Meta();
            Context context = ContextHolder.get();

            meta.transactionId = context.getTransactionId();
            meta.processName = context.getProcessName();
            meta.status = status;

            return meta;
        }

        @Override
        public Meta clone() {
            Meta meta = new Meta();

            meta.other.putAll(other);

            meta.transactionId = transactionId;
            meta.processName = processName;
            meta.status = status;

            return meta;
        }

    }

    @Data
    @EqualsAndHashCode(callSuper=false)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Error extends JsonAnyProperties implements Cloneable, Serializable {

        private static final long serialVersionUID = 1020451348629970064L;

        private String tid;
        private String codigo;
        private String origem;
        private String tipo;
        private String subtipo;
        private String msg;
        private String detalhes;

        @Override
        public Error clone() {
            Error error = new Error();

            error.other.putAll(other);

            error.tid = tid;
            error.codigo = codigo;
            error.origem = origem;
            error.tipo = tipo;
            error.subtipo = subtipo;
            error.msg = msg;
            error.detalhes = detalhes;

            return error;
        }

    }

}
