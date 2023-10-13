package cn.tuling.webfluxr2dbcmysql.service;

import cn.tuling.webfluxr2dbcmysql.dao.UserDao;
import cn.tuling.webfluxr2dbcmysql.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * @author : ichigo-xin
 * @date 2023/10/13
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;


    public Mono<User> addUser(User user) {
        Mono<User> save = userDao.save(user);
        return save;
    }

    public Mono<Void> deleteUser(Long userId) {
        return userDao.existsById(userId).flatMap(
                item -> {
                    if (item) {
                        return userDao.deleteById(userId);
                    }
                    return Mono.empty();
                }
        );
    }
}
