package cn.tuling.webfluxstart.entity;

import lombok.Builder;
import lombok.Data;

/**
 * @author : ichigo-xin
 * @date 2023/10/13
 */
@Data
@Builder
public class User {

    private Integer id;
    private String username;
}
