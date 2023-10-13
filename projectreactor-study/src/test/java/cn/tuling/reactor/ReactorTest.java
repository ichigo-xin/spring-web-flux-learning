package cn.tuling.reactor;

import org.junit.Test;
import reactor.core.publisher.Flux;

/**
 * @author : ichigo-xin
 * @date 2023/10/13
 */
public class ReactorTest {


    @Test
    public void fluxTest() {
        Flux<String> just = Flux.just("beijing", "shanghai", "chengdu")
                .map(item -> {
                    if (item.equals("shanghai")) {
                        int i = 9 / 0;
                        return item;
                    }
                    return item;
                });

        just.subscribe(System.out::println, err -> System.out.println(err.getMessage()));
    }


}
