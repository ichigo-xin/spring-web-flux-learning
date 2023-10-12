package cn.tuling.servlet.threadpool;

import lombok.Builder;
import org.springframework.aop.ThrowsAdvice;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author : ichigo-xin
 * @date 2023/10/12
 */
@Builder
public class AsyncThreadFactory implements ThreadFactory {

    private final ThreadFactory defaultFactory = Executors.defaultThreadFactory();

    private final AtomicInteger threadNumber = new AtomicInteger(1);

    private String threadName;

    @Override
    public Thread newThread(Runnable r) {
        Thread thread = defaultFactory.newThread(r);
        thread.setName(this.threadName + "-" + threadNumber.getAndIncrement());
        return thread;
    }
}
