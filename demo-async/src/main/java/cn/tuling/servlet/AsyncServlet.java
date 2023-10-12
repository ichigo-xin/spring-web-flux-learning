package cn.tuling.servlet;

import cn.tuling.servlet.threadpool.*;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * @author : ichigo-xin
 * @date 2023/10/12
 */

@WebServlet(value = "/async", asyncSupported = true)
@Slf4j
public class AsyncServlet extends HttpServlet {

    private ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 1, 0L,
            TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>(1),
            AsyncThreadFactory.builder().threadName("async-thread-pool-").build(), new AsyncRejectedExecutionHandler());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("request:{},current Thread:{}", req.getQueryString(), Thread.currentThread().getName());
        AsyncContext asyncContext = req.startAsync();
        AsyncRequestWrapper asyncRequestWrapper = AsyncRequestWrapper.builder()
                .asyncContext(asyncContext)
                .request(asyncContext.getRequest())
                .response(asyncContext.getResponse())
                .thread(Thread.currentThread())
                .build();
        executor.execute(asyncRequestWrapper);
    }
}
