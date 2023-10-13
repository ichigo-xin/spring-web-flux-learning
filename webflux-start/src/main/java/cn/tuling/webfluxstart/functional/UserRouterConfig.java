package cn.tuling.webfluxstart.functional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import java.util.List;

/**
 * @author : ichigo-xin
 * @date 2023/10/13
 */


@Configuration
public class UserRouterConfig {

    @Bean
    public RouterFunction<ServerResponse> userRouterFunctions(UserHandler handler) {
        return RouterFunctions.route().filter((request, next) -> {
                    ServerRequest.Headers headers = request.headers();
                    List<String> token = headers.header("token");
                    if (token == null || token.isEmpty()) {
                        return ServerResponse.status(HttpStatus.FORBIDDEN).build();
                    }
                    return next.handle(request);
                })
                .GET("/functional/user/get", handler::getUser).build();
    }
}
