package cn.tuling.webfluxr2dbcmysql.dao;

import cn.tuling.webfluxr2dbcmysql.entity.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

/**
 * @author : ichigo-xin
 * @date 2023/10/13
 */
public interface UserDao extends ReactiveCrudRepository<User, Long> {
}
