package cn.tuling;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * @author : ichigo-xin
 * @date 2023/10/12
 */
@SpringBootApplication
@ServletComponentScan
public class DemoAsyncApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoAsyncApplication.class, args);
    }
}
