package com.excilys.librarymanager.servlet;

import javax.servlet.http.*;
import javax.servlet.*;
import java.util.*;
import java.text.*;
import java.io.*;

import com.excilys.librarymanager.exception.ServletException;
import java.io.IOException;

public class DashboardServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("webapp/WEB-INF/View/dashboard.jsp");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>bjr</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Bonjour le monde !</h1>");
        out.println("</body>");
        out.println("</html>");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doGet(request, response);
    }
}