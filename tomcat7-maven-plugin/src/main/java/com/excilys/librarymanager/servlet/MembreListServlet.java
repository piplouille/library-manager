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

import java.util.List;

import com.excilys.librarymanager.model.Membre;
import com.excilys.librarymanager.service.MembreServiceImpl;

public class MembreListServlet extends HttpServlet {
    /*
    Une méthode :
    doGet() : afficher membres
    */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Et récupérer le membre correspondant s'il existe
        MembreServiceImpl membre_service = MembreServiceImpl.getInstance();
        List<Membre> liste = null;

        try {
            liste = membre_service.getList();
        }
        catch (Exception e) {
            System.out.println("Exception Message " + e.getLocalizedMessage());
        }

        request.setAttribute("membres", liste);
                
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/membre_list.jsp");
        dispatcher.forward(request, response);
    }
}