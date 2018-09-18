package io.xmacedo;


import io.xmacedo.client.rest.Response;
import io.xmacedo.context.Context;
import io.xmacedo.exception.Exception;
import io.xmacedo.exception.ExceptionEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

    @Autowired
    private Context context;

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Response<Void> handleOfertizeException(HttpServletResponse httpServletResponse, Exception exception) {
        ExceptionEnum.Description description = exception.getError().getDescription();

        Response response = new Response();
        Response.Meta  meta         = Response.Meta.fromContext("error");
        Response.Error error        = new Response.Error();

        response.setMeta(meta);
        response.setData(new Object());
        response.setError(error);

        // set response variables
        httpServletResponse.setStatus(description.getStatus().value());

        error.setTid(this.context.getTransactionId());
        error.setCodigo(description.getCode()); 
        error.setOrigem(description.getOrigin().name());
        error.setTipo(description.getType().name());
        error.setSubtipo(description.getSubtype().name());
        error.setMsg(description.getMsg());
        error.setDetalhes(description.getDetails());

        return response;
    }

}
