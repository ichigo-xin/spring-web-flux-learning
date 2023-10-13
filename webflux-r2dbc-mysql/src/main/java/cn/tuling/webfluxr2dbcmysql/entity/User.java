package cn.tuling.webfluxr2dbcmysql.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

/**
 * @author : ichigo-xin
 * @date 2023/10/13
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Table("user_t")
public class User {

    @Id
    private Long id;

    private String username;

    private LocalDateTime birthday;
}
