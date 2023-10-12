package cn.tuling.servlet.threadpool;

import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.AsyncContext;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author : ichigo-xin
 * @date 2023/10/12
 */

@Data
@Slf4j
@Builder
public class AsyncRequestWrapper implements Runnable {
    
    private AsyncContext asyncContext;
    private ServletRequest request;
    
    private ServletResponse response;
    
    private Thread thread;
    
    
    @Override
    public void run() {
        processFuther(asyncContext, request, response);
    }

    private void processFuther(AsyncContext asyncContext, ServletRequest request, ServletResponse response) {
        sleep(10);
        try {
            response.getWriter().println("async");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        asyncContext.complete();
    }

    private void sleep(int i) {
        try {
            TimeUnit.SECONDS.sleep(i);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
