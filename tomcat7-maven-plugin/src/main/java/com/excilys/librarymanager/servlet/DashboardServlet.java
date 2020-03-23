package com.excilys.librarymanager.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
// import java.util.*;
// import java.text.*;
// import java.io.*;

import java.io.IOException;

import com.excilys.librarymanager.exception.ServiceException;

import com.excilys.librarymanager.service.MembreServiceImpl;
import com.excilys.librarymanager.dao.MembreDaoImpl;
import com.excilys.librarymanager.model.Membre;

import java.util.ArrayList;
import java.util.List;

public class DashboardServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            MembreServiceImpl membre_service = MembreServiceImpl.getInstance();
            int id_membre = membre_service.create("Debesse", "Laetitia", "1 rue", "yo@ensta.fr", "06");
            List<Membre> liste = membre_service.getList();
            // int no_membres = membre_service.count();
            // request.setAttribute("no_membres", no_membres);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/dashboard.jsp");
            dispatcher.forward(request, response);
        }
        catch (ServiceException e) {
            System.out.println("Exception Message " + e.getLocalizedMessage());
        }
    }
}