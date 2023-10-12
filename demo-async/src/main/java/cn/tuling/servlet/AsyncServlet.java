package cn.tuling.servlet;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author : ichigo-xin
 * @date 2023/10/12
 */

@WebServlet(value = "/sync")
@Slf4j
public class SyncServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("request:{},{}:current Thread:", req.getQueryString(), Thread.currentThread().getName());
        processFuther(req, resp);
    }

    private void processFuther(HttpServletRequest req, HttpServletResponse resp) {
        try {
            sleep(10);
            resp.getWriter().println("sync");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void sleep(int i) {
        try {
            TimeUnit.SECONDS.sleep(i);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
