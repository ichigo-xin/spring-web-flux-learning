package cn.tuling.webfluxstart.functional;

import cn.tuling.webfluxstart.entity.User;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Optional;
import java.util.Random;
import java.util.UUID;

/**
 * @author : ichigo-xin
 * @date 2023/10/13
 */
@Component
public class UserHandler {

    public Mono<ServerResponse> getUser(ServerRequest request) {
        return processGetUser(request);
    }

    public Mono<ServerResponse> processGetUser(ServerRequest request) {
        Optional<String> userId = request.queryParam("userId");
        boolean parent = userId.isPresent();
        Integer id = 0;
        if (!parent) {
            id = new Random(100).nextInt();
        } else {
            id = Integer.valueOf(userId.get());
        }
        Mono<User> just = Mono.just(User.builder().id(id).username(UUID.randomUUID().toString()).build())
                .map(user -> {
                    if (user.getId().equals(100)) {
                        throw new RuntimeException("userId limited!");
                    }
                    return user;
                });
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(just, User.class);
    }
}
