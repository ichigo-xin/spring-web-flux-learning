package cn.tuling.webfluxstart.annotated;

import cn.tuling.webfluxstart.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : ichigo-xin
 * @date 2023/10/13
 */
@RestController
@RequestMapping("/annotated")
public class AnnotatedController {

    @GetMapping("/user/get")
    public User getUser() {
        return User.builder().build();
    }
}
