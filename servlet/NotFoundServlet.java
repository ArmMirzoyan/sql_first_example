package com.example.tomcattest.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * All the requests not handled by other Servlets will be handled
 * by this Servlet. URL-pattern /* just covers all cases.
 */
@WebServlet(name = "NotFoundServlet", value = "/items/*")
public class NotFoundServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        response.getWriter().println("NOT_FOUND");
    }
}
