package cn.tuling.servlet.threadpool;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author : ichigo-xin
 * @date 2023/10/12
 */
public class AsyncRejectedExecutionHandler implements RejectedExecutionHandler {

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        AsyncRequestWrapper asyncRequestWrapper = (AsyncRequestWrapper) r;
        try {
            asyncRequestWrapper.getResponse().getWriter().println("too many request");
        } catch (Exception e) {
            e.printStackTrace();
        }
        asyncRequestWrapper.getAsyncContext().complete();

    }
}
