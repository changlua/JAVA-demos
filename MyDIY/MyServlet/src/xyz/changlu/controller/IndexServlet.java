package xyz.changlu.controller;

import xyz.changlu.servlet.Request;
import xyz.changlu.servlet.Response;
import xyz.changlu.servlet.GenericServlet;

/**
 * @ClassName IndexServlet
 * @Author ChangLu
 * @Date 2021/5/17 20:59
 * @Description TODO
 */
public class IndexServlet extends GenericServlet {

    @Override
    public void service(Request request, Response response) {
        response.write("<h1>index</h1>");
    }
}