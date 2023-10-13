package cn.tuling.webfluxstart.global;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebExceptionHandler;
import reactor.core.publisher.Mono;

/**
 * @author : ichigo-xin
 * @date 2023/10/13
 */
@Configuration
@Order(-2)
public class MyWebExceptionHandler implements WebExceptionHandler {

    @Autowired
    private ObjectMapper objectMapper;
    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        ResultWrapper build = ResultWrapper.builder().message("unknown").build();
        if (ex instanceof RuntimeException) {
            build.setMessage(ex.getMessage());
        }
        byte[] bytes = null;
        try {
            bytes = objectMapper.writeValueAsBytes(build);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            bytes = "".getBytes();
        }
        DataBufferFactory dataBufferFactory = exchange.getResponse().bufferFactory();
        DataBuffer wrap = dataBufferFactory.wrap(bytes);
        return exchange.getResponse().writeWith(Mono.just(wrap));
    }
}
