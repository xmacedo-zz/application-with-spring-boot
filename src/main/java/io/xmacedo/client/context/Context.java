package io.xmacedo.client.context;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.servlet.http.HttpServletRequest;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Context {

    private String transactionId;
    private String processName;

    public static Context from(HttpServletRequest request) {
        Context context = new Context();

        context.transactionId = request.getHeader("transactionId");
        context.processName = request.getRequestURI();

        return context;
    }

}
