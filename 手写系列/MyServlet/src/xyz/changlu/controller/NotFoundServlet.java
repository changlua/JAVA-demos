package xyz.changlu.controller;

import xyz.changlu.servlet.Request;
import xyz.changlu.servlet.Response;
import xyz.changlu.servlet.HttpServlet;

/**
 * @ClassName NotFoundServlet
 * @Author ChangLu
 * @Date 2021/5/17 21:20
 * @Description TODO
 */
public class NotFoundServlet extends HttpServlet {

    @Override
    public void doGet(Request request, Response response) {
        response.write("<h1>404页面</h1>");
    }
}