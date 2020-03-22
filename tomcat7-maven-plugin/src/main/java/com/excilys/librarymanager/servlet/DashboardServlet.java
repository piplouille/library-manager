package com.excilys.librarymanager.servlet;

import javax.servlet.http.*;
import javax.servlet.*;
import java.util.*;
import java.text.*;
import java.io.*;

import com.excilys.librarymanager.exception.ServletException;
import java.io.IOException;

public class DashboardServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            this.getServletContext().getRequestDispatcher("/WEB-INF/View/dashboard.jsp").forward(request, response);
        }
        catch (IOException e) {
            throw new ServletException("Erreur : vtff");
        }
        catch (Exception e) {
            throw new IOException("help");
        }
        
    }

}