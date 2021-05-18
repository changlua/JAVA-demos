package xyz.changlu.controller;

import xyz.changlu.servlet.Request;
import xyz.changlu.servlet.Response;
import xyz.changlu.servlet.HttpServlet;

/**
 * @ClassName HomeServlet
 * @Author ChangLu
 * @Date 2021/5/17 21:12
 * @Description TODO
 */
public class HomeServlet extends HttpServlet {

    @Override
    public void doGet(Request request, Response response) {
        response.write("<h1>home</h1>");
    }
}