package xyz.changlu.controller;

import xyz.changlu.servlet.Request;
import xyz.changlu.servlet.Response;
import xyz.changlu.servlet.HttpServlet;

/**
 * @ClassName InfoServlet
 * @Author ChangLu
 * @Date 2021/5/17 21:13
 * @Description TODO
 */
public class InfoServlet extends HttpServlet {

    @Override
    public void doGet(Request request, Response response) {
        response.write("<h1>info</h1>");
    }
}