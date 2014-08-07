package com.example.servlet;

import com.example.util.AsyncHelper;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "AsyncServlet",
        urlPatterns = { "/async" },
        asyncSupported = true)
public class AsyncServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        final AsyncContext asyncContext = AsyncHelper.getAsyncContext(req, resp);
        asyncContext.start(new Runnable() {
            @Override
            public void run() {
                resp.setContentType("text/html");
                try {
                    PrintWriter writer = resp.getWriter();
                    writer.println("<html><body><h5>AsyncServlet</h5><p>paragraph</p></body></html>");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                asyncContext.complete();
            }
        });
    }
}
