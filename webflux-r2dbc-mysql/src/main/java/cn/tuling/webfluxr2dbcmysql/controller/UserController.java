package cn.tuling.webfluxr2dbcmysql.controller;

import cn.tuling.webfluxr2dbcmysql.entity.User;
import cn.tuling.webfluxr2dbcmysql.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @author : ichigo-xin
 * @date 2023/10/13
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user/save")
    public Mono<User> saveUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @PostMapping("/user/delete")
    public Mono<Void> deleteUser(Long userId) {
        return userService.deleteUser(userId);
    }
}
